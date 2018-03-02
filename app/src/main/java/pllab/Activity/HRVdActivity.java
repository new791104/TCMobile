package pllab.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;

import org.joda.time.LocalDate;

import Global.GV;
import Http.Network_core;
import cn.refactor.lib.colordialog.PromptDialog;
import pllab.tcmobile.R;

public class HRVdActivity extends AppCompatActivity {

    private Network_core nCore = new Network_core(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrvd);
        //關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 製作 update list
        GV.update_list.setDataType("HRV");
        GV.update_list.setUserID(GV.query_list.getUserID());

        Spinner spinner_deviceID = findViewById(R.id.hrvd_deviceID_spinner);
        ArrayAdapter<CharSequence> deviceIdList;
        deviceIdList = ArrayAdapter.createFromResource(this,R.array.deviceID_HRV,android.R.layout.simple_spinner_dropdown_item);
        AdapterView.OnItemSelectedListener deviceID_Listener = setSpinnerListener();
        spinner_deviceID.setAdapter(deviceIdList);
        spinner_deviceID.setOnItemSelectedListener(deviceID_Listener);

        Button finish_but = findViewById(R.id.finish_button);
        finish_but.setOnClickListener(setFinishButListener());

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
