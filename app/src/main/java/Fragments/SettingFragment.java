package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import pllab.Activity.MainActivity;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/11/2.
 */

// TODO 製作Query

public class SettingFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);

        Spinner spinner_dataType = rootView.findViewById(R.id.dataType_spinner);
        Spinner spinner_deviceID = rootView.findViewById(R.id.deviceID_spinner);
        Spinner spinner_startDate = rootView.findViewById(R.id.dataStartDate_spinner);
        Spinner spinner_endDate = rootView.findViewById(R.id.dataEndDate_spinner);

        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.dataType,
                android.R.layout.simple_spinner_item);
        spinner_dataType.setAdapter(typeList);



        return rootView;
    }
}
