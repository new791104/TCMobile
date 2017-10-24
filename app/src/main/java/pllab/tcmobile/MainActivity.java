package pllab.tcmobile;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import Http.Network_core;
import Objects.HRV;
import pllab.tcmobile.R;

public class MainActivity extends AppCompatActivity {

    private Network_core nCore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
         * ImageLoader
         */
        ImageView SPO2 = (ImageView) findViewById(R.id.image);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			.build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage("http://xenon.ym.edu.tw/spo2/drawspo2.php?xid=00920040&date=10/12&type=chart", SPO2);
        /*
         * ButtomBar
         */
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }
            }
        });
//        final HRV[] resHRV = new HRV[1];
//        nCore = new Network_core(this);
//        //View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
//        final TextView HR_text = (TextView) findViewById(R.id.HR);
//        final TextView V_text = (TextView) findViewById(R.id.V);
//        final TextView PA_text = (TextView) findViewById(R.id.PA);
//        final TextView TE_text = (TextView) findViewById(R.id.TE);
//        final TextView Bat_text = (TextView) findViewById(R.id.Bat);
//
//        nCore.requestKY("HRV");
//        nCore.setCallback(new Network_core.netCallback() {
//
//            @Override
//            public String response(String response) {
//                Gson gson = new Gson();
//                resHRV[0] = gson.fromJson(response, new TypeToken<HRV>(){}.getType());
//                HR_text.setText("HR: " + resHRV[0].getHR());
//                V_text.setText("V: " + resHRV[0].getVAR());
//                PA_text.setText("PA: " + resHRV[0].getPA());
//                TE_text.setText("TE: " + resHRV[0].getTE());
//                Bat_text.setText("Bat: " + resHRV[0].getBAT());
//                return null;
//            }
//        });

    }
}
