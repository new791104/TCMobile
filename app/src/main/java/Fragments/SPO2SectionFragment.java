package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import Http.Network_core;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/10/25.
 */

public class SPO2SectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    private Network_core nCore;

    public SPO2SectionFragment(Context nContext) {
        context = nContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spo2, container, false);

        /*
         * ImageLoader
         */
        ImageView SPO2 = (ImageView) rootView.findViewById(R.id.SPO2_image);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(rootView.getContext())
			    .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage("http://xenon.ym.edu.tw/spo2/drawspo2.php?xid=00920040&date=10/12&type=chart", SPO2);

        return rootView;
    }
}
