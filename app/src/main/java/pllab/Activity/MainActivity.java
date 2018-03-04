package pllab.Activity;

import android.support.annotation.IdRes;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import Adapter.SelectMainPagerAdapter;
import Global.GV;
import Http.Network_core;
import pllab.tcmobile.R;

public class MainActivity extends AppCompatActivity{

    private SelectMainPagerAdapter mSelectMainPagerAdapter;
    private Network_core nCore;
    private boolean lock = false;
    public BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 關閉系統狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mSelectMainPagerAdapter = new SelectMainPagerAdapter(getSupportFragmentManager(), this);
        GV.mViewPager = findViewById(R.id.pager);
        GV.mViewPager.setScrollingEnabled(false);
        GV.mViewPager.requestDisallowInterceptTouchEvent(false);
        GV.mViewPager.setAdapter(mSelectMainPagerAdapter);

        /*
         * BottomBar
         */
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = 0;
                if (tabId == R.id.tab_setQuery) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    position = 1;
                }
//                else if (tabId == R.id.tab_healthList) {
//                    position = 2;
//                }
                else if (tabId == R.id.tab_healthDetect) {
                    position = 2;
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
                InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(GV.mViewPager.getWindowToken(), 0); // 強制隱藏鍵盤
                if (!lock) {
                    lock = true;
                    bottomBar.selectTabAtPosition(position);
                    lock = false;
                }
            }
        });

        GV.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                GV.mViewPager.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
