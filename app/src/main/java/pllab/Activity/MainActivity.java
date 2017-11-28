package pllab.Activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import Adapter.SectionsPagerAdapter;
import Global.GV;
import Http.Network_core;
import Objects.Dummy;
import pllab.tcmobile.R;

import static pllab.tcmobile.R.id.bottomBar;

public class MainActivity extends AppCompatActivity{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Network_core nCore;
    private boolean lock = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        GV.mViewPager = findViewById(R.id.pager);
        GV.mViewPager.setScrollingEnabled(false);
        GV.mViewPager.requestDisallowInterceptTouchEvent(false);
        GV.mViewPager.setAdapter(mSectionsPagerAdapter);

        /*
         * ButtomBar
         */
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = 0;
                if (tabId == R.id.tab_setQuery) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    position = 1;
                }
                else if (tabId == R.id.tab_healthList) {
                    position = 2;
                }
                else if (tabId == R.id.tab_healthReport) {
                    position = 3;
                }
                else {
                    position = 0;
                }
                Log.e("debug", "tab position: "+position);
                if (!lock) {
                    lock = true;
                    GV.mViewPager.setCurrentItem(position);
                    lock = false;
                }
            }
        });

        GV.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.e("debug", "onPageScrolled: " + position);
                if (!lock) {
                    lock = true;
                    bottomBar.selectTabAtPosition(position);
                    lock = false;
                }
            }
        });

    }
}
