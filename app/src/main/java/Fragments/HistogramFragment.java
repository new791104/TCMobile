package Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapter.histogram_RecyclerViewAdapter;
import Global.GV;
import Http.Network_core;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/10/25.
 */

// TODO 以圖表形式呈現資料

public class HistogramFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private RecyclerView cardRV;
    private histogram_RecyclerViewAdapter cardRVAdapter;
    private Context hisContext;
    private Network_core nCore;
    private List<Integer> colorList;

    public HistogramFragment(List<Integer> colorlist) {
        colorList = colorlist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("debug", "###onCreateView###");
        View rootView = inflater.inflate(R.layout.fragment_histogram, container, false);

        // card Recycler Vew
        cardRVAdapter = new histogram_RecyclerViewAdapter(rootView.getContext(), colorList);

        // 關閉滑動效果
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(getContext());
        linearLayoutManager.setScrollEnabled(false);

        cardRV = rootView.findViewById(R.id.histogram_recyclerView);
        cardRV.setLayoutManager(linearLayoutManager);

        //cardRVAdapter.notify();
        cardRV.setAdapter(cardRVAdapter);     //設定適配器
        cardRV.scrollToPosition(0);

        return rootView;
    }
}
