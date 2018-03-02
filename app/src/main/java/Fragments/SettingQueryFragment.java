package Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.util.Locale;

import Global.GV;
import Http.Network_core;
import Objects.HRV;
import Objects.SPO2;
import Objects.tableList;
import pllab.Activity.HistogramActivity;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/11/2.
 */

// TODO 製作Query

public class SettingQueryFragment extends Fragment {

    private Network_core nCore;
    private CompactCalendarView compactCalendarView;
    private boolean shouldShow = true;
    public static final String ARG_SECTION_NUMBER = "section_number";

    public SettingQueryFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        nCore = new Network_core(rootView.getContext());
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);

        Spinner spinner_dataType = rootView.findViewById(R.id.dataType_spinner);
        final Spinner spinner_deviceID = rootView.findViewById(R.id.hrvd_deviceID_spinner);
        final EditText editText_startDate = (EditText) rootView.findViewById(R.id.startDate_editText);
        final EditText editText_endDate = (EditText) rootView.findViewById(R.id.endDate_editText);
        final Button button_confirm = rootView.findViewById(R.id.confirm_button);

        // Set dataType Spinner Listener
        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.dataType,
                android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence>[] deviceIdList = new ArrayAdapter[]{ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.deviceID_HRV,
                android.R.layout.simple_spinner_dropdown_item)};
        AdapterView.OnItemSelectedListener dataType_Listener = setSpinnerListener(deviceIdList, rootView, spinner_deviceID);
        spinner_dataType.setAdapter(typeList);
        spinner_dataType.setOnItemSelectedListener(dataType_Listener);

        // init deviceID spinner
        deviceIdList[0] = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.deviceID_HRV,
                android.R.layout.simple_spinner_dropdown_item);
        spinner_deviceID.setAdapter(deviceIdList[0]);

        // Set Date
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener startdate = dateOnClickListener(myCalendar, editText_startDate),
                enddate = dateOnClickListener(myCalendar, editText_endDate);
        editText_startDate.setOnClickListener(etOnListener(rootView, startdate, myCalendar));
        editText_endDate.setOnClickListener(etOnListener(rootView, enddate, myCalendar));

        // Submit Button
        button_confirm.setOnClickListener(confirmListener());

        return rootView;
    }

    private AdapterView.OnItemSelectedListener setSpinnerListener(final ArrayAdapter<CharSequence>[] deviceIdList, final View rootView, final Spinner spinner_deviceID) {
        return new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView, View view,
                                       int position, long id) {
                if (adapterView.getSelectedItem().toString().equals("HRV")) {
                    deviceIdList[0] = ArrayAdapter.createFromResource(rootView.getContext(),
                            R.array.deviceID_HRV,
                            android.R.layout.simple_spinner_item);
                }
                else if (adapterView.getSelectedItem().toString().equals("SPO2")) {
                    deviceIdList[0] = ArrayAdapter.createFromResource(rootView.getContext(),
                            R.array.deviceID_SPO2,
                            android.R.layout.simple_spinner_item);
                }
                // Set deviceID Spinner
                spinner_deviceID.setAdapter(deviceIdList[0]);
                spinner_deviceID.setOnItemSelectedListener(setDeviceIdListener());

                GV.query_list.setDataType(adapterView.getSelectedItem().toString());

            }
            @Override
            public void onNothingSelected(AdapterView arg0) {
            }
        };
    }

    private AdapterView.OnItemSelectedListener setDeviceIdListener() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                GV.query_list.setDeviceID(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private AdapterView.OnClickListener getCalendarExposeLis() {
        return new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendarWithAnimation();
                        compactCalendarView.setVisibility(View.VISIBLE);
                    } else {
                        compactCalendarView.hideCalendarWithAnimation();
                        compactCalendarView.setVisibility(View.INVISIBLE);
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }

    private DatePickerDialog.OnDateSetListener dateOnClickListener(final Calendar myCalendar, final EditText editText_date) {
        return new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINESE);
                if (editText_date.getId() == R.id.startDate_editText) {
                    GV.query_list.setDataStartDate(new LocalDate(sdf.format(myCalendar.getTime())));
                }
                else if (editText_date.getId() == R.id.endDate_editText)
                    GV.query_list.setDataEndDate(new LocalDate(sdf.format(myCalendar.getTime())));

                editText_date.setText(sdf.format(myCalendar.getTime()));
            }
        };
    }

    private View.OnClickListener etOnListener(final View rootView, final DatePickerDialog.OnDateSetListener date, final Calendar myCalendar) {
        return new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog(rootView.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        };
    }

    // TODO Confirm Listener: Get data from Server.
    private View.OnClickListener confirmListener() {
        final String datatype = GV.query_list.getDataType();
        final String userid = GV.query_list.getUserID();
        final String deviceid = GV.query_list.getDeviceID();
        final LocalDate startdate = GV.query_list.getDataStartDate();
        final LocalDate enddate = GV.query_list.getDataEndDate();
        final Gson gson = new Gson();
        final String[] datatpye = new String[1];
        return new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO 之後要改成傳 Object
                nCore.serverQueryGet(GV.query_list.getDataType(), GV.query_list.getUserID(), GV.query_list.getDeviceID(), GV.query_list.getDataStartDate(), GV.query_list.getDataEndDate());
                nCore.setCallback(new Network_core.netCallback() {
                    @Override
                    public String response(String response) {
                        GV.tablelist = gson.fromJson(response, new TypeToken<tableList>() {
                        }.getType());
                        datatpye[0] =  GV.tablelist.getType();
                        if (datatpye[0].equals("HRV")) {
                            GV.hrvTable = gson.fromJson(response, new TypeToken<tableList<HRV>>() {
                            }.getType());
                        } else if (datatpye[0].equals("SPO2")) {
                            GV.spo2Table = gson.fromJson(response, new TypeToken<tableList<SPO2>>() {
                            }.getType());
                        }

                        if (response.indexOf("arrayList") >= 0) {
                            Log.e("debug", "response.indexOf(\"arrayList\") >= 0");
                        }
                        // GV.mViewPager.setCurrentItem(2);
//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.setting_layout, new HistogramFragment(), null)
//                                .addToBackStack(null)
//                                .commit();
                        Intent intent = new Intent(getActivity(), HistogramActivity.class);
                        startActivity(intent);
                        return null;
                    }
                });
            }
        };
    }
}
