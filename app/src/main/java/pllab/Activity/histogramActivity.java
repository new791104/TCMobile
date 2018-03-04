package pllab.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import Adapter.SelectMainPagerAdapter;
import Fragments.HistogramFragment;
import Global.GV;
import Objects.disScrollingPager;
import pllab.tcmobile.R;

public class HistogramActivity extends AppCompatActivity {
    private SelectMainPagerAdapter mSelectMainPagerAdapter;
    disScrollingPager hisViewPager;
    private LayoutInflater mInflater;
    private ViewGroup mViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        // 關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 客製 ToolBar
        Toolbar toolbar = findViewById(R.id.histo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(GV.tablelist.getType().toString());
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );

        /*
            在 Activity 中引入 Fragment 的方法: 呼叫FragmentManager
         */
        Fragment fragment = new HistogramFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_autoRelativeLayout, fragment)
                .commit();

//        mSelectMainPagerAdapter = new SelectMainPagerAdapter(getSupportFragmentManager(), this);
//        hisViewPager = findViewById(R.id.pager);
//        hisViewPager.setScrollingEnabled(false);
//        hisViewPager.requestDisallowInterceptTouchEvent(false);
//        hisViewPager.setAdapter(mSelectMainPagerAdapter);



    }

}
