package Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Http.Network_core;
import Objects.HRV;
import pllab.tcmobile.R;
import pro.alexzaitsev.freepager.library.view.infinite.InfiniteVerticalPager;
import pro.alexzaitsev.freepager.library.view.infinite.ViewFactory;

/**
 * Created by charlie on 2017/10/25.
 */

public class HRVSectionFragment extends Fragment implements ViewFactory {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    private Network_core nCore;
    private int mBgColor;
    private LayoutInflater mInflater;
    private ViewGroup mViewGroup;

    public HRVSectionFragment(Context nContext) {
        context = nContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = inflater;
        mViewGroup = container;
        nCore = new Network_core(context);
        /*
         * freepager
         */
        mBgColor = getResources().getColor(R.color.context);
        InfiniteVerticalPager verticalPager = (InfiniteVerticalPager) inflater
                .inflate(R.layout.fragment_infinite_vertical, container, false);
        verticalPager.setFactory(this);
        verticalPager.instantiate();
        return verticalPager;
    }

    /*
     * 會在 vertical - 1 生成 View
     */
    @Override
    public View makeView(int vertical, int horizontal) {
        Log.e("debug", "vertical: " + vertical);
        if (vertical == -1) {
            View rootView = mInflater.inflate(R.layout.fragment_hrv, mViewGroup, false);
            return getHRV_text(rootView);
        }
        Button btn = new Button(getActivity());
        btn.setText("Vertical " + vertical);
        btn.setBackgroundColor(mBgColor);

        return btn;
    }

    /*
     * HRV request
     */
    private View getHRV_text(View rootView) {

        final HRV[] resHRV = new HRV[1];

//        nCore.requestKY("HRV");
//        nCore.setCallback(new Network_core.netCallback() {
//
//            @Override
//            public String response(String response) {
//                Gson gson = new Gson();
//                resHRV = gson.fromJson(response, new TypeToken<HRV>(){}.getType());
//
//                return null;
//            }
//        });
        return rootView;
    }
}
