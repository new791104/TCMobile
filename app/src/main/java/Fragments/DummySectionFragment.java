package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/10/25.
 */

public class DummySectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public DummySectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nodata, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 0:
                dummyTextView.setText("設定頁面");
                break;
            case 1:
                dummyTextView.setText("表單");
                break;
            case 2:
                dummyTextView.setText("圖表");
                break;
        }

        return rootView;
    }
}