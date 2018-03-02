package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Adapter.detect_RecyclerViewAdapter;
import pllab.tcmobile.R;

/**
 * Created by cminer on 2018/2/28.
 */

public class DetectiveFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private RecyclerView cardRV;
    private detect_RecyclerViewAdapter cardRVAdapter;

    public static final String ARG_SECTION_NUMBER = "section_number";

    public DetectiveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detect, container, false);
        getActivity();
        // card Recycler Vew
        cardRVAdapter = new detect_RecyclerViewAdapter(rootView.getContext());
        cardRV = rootView.findViewById(R.id.detect_recyclerView);
        cardRV.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        cardRV.setAdapter(cardRVAdapter);     //設定適配器
        return rootView;
    }
}
