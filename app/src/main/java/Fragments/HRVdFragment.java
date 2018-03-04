package Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.zhy.autolayout.AutoRelativeLayout;

import Global.GV;
import Http.Network_core;
import cn.refactor.lib.colordialog.PromptDialog;
import pllab.tcmobile.R;

/**
 * Created by cminer on 2018/3/2.
 */

public class HRVdFragment extends Fragment {
    private Context context;
    private Network_core nCore = new Network_core(getContext());

    public HRVdFragment(Context c) {
        context = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("debug", "###onCreateView###");
        AutoRelativeLayout autoRelativeLayout = (AutoRelativeLayout) inflater.inflate(R.layout.activity_hrvd, container);

        // 製作 update list
        GV.update_list.setDataType("HRV");
        GV.update_list.setUserID(GV.query_list.getUserID());

        Spinner spinner_deviceID = container.findViewById(R.id.hrvd_deviceID_spinner);
        ArrayAdapter<CharSequence> deviceIdList;
        deviceIdList = ArrayAdapter.createFromResource(context, R.array.deviceID_HRV, android.R.layout.simple_spinner_dropdown_item);
        AdapterView.OnItemSelectedListener deviceID_Listener = setSpinnerListener();
        spinner_deviceID.setAdapter(deviceIdList);
        spinner_deviceID.setOnItemSelectedListener(deviceID_Listener);

        Button finish_but = container.findViewById(R.id.finish_button);
        finish_but.setOnClickListener(setFinishButListener());

        return autoRelativeLayout;
    }

    private AdapterView.OnItemSelectedListener setSpinnerListener() {
        return new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView, View view,
                                       int position, long id) {
                GV.update_list.setDeviceID(adapterView.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView arg0) {
            }
        };
    }

    private View.OnClickListener setFinishButListener() {
        return new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(final View v) {
                // TODO 之後要改成傳 Object
                nCore.serverUpdateGet("HRV", GV.update_list.getUserID(), GV.update_list.getDeviceID());
                nCore.setCallback(new Network_core.netCallback() {
                    @Override
                    public String response(String response) {
                        Log.e("view-response", "" + response);
                        if(response.equals("1")){
                            new PromptDialog(v.getContext())
                                    .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                                    .setAnimationEnable(true)
                                    .setTitleText("Success")
                                    .setContentText("資料上傳成功")
                                    .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                                        @Override
                                        public void onClick(PromptDialog dialog) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                        else {
                            new PromptDialog(v.getContext())
                                    .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                    .setAnimationEnable(true)
                                    .setTitleText("Wrong")
                                    .setContentText("資料上傳失敗")
                                    .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                                        @Override
                                        public void onClick(PromptDialog dialog) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }

                        return null;
                    }
                });
            }
        };
    }
}
