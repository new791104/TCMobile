package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import Adapter.cardRecyclerViewAdapter;
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
    private cardRecyclerViewAdapter cardRVAdapter;
    private Context hisContext;
    private Network_core nCore;

    public HistogramFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("debug", "###onCreateView###");
        View rootView = inflater.inflate(R.layout.fragment_histogram, container, false);

        // card Recycler Vew
        cardRVAdapter = new cardRecyclerViewAdapter(rootView.getContext());
        cardRV = rootView.findViewById(R.id.histogram_recyclerView);
        cardRV.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        cardRV.setAdapter(cardRVAdapter);     //設定適配器

        return rootView;
    }
}
