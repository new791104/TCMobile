package pllab.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.karumi.expandableselector.ExpandableItem;
import com.karumi.expandableselector.ExpandableSelector;
import com.karumi.expandableselector.OnExpandableItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapter.SelectMainPagerAdapter;
import Adapter.paramHint_RecyclerViewAdapter;
import Fragments.HistogramFragment;
import Global.GV;
import Objects.disScrollingPager;
import pllab.tcmobile.R;

public class HistogramActivity extends AppCompatActivity {
    private SelectMainPagerAdapter mSelectMainPagerAdapter;
    //private
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
            在 Activity 中引入 Fragment 的方法: 呼叫 FragmentManager
            在此引入 Histogram RecyclerView
         */
        final HistogramFragment hisFragment = new HistogramFragment(randomColor(GV.tablelist.getDatatable().size()));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.histo_autoRelativeLayout, hisFragment)
                .commit();

        final RecyclerView paramRV = (RecyclerView) findViewById(R.id.paramHint_recyclerView);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        paramRV.setLayoutManager(mLayoutManager);
        paramHint_RecyclerViewAdapter paramRVA = new paramHint_RecyclerViewAdapter(hisFragment);
        paramRV.setAdapter(paramRVA);

        // ExpandableSelector
        //initializeExpandableSelector();
    }

    /**
     * 製作隨機顏色 List
     * @param colorListSize 顏色 List 長度
     * @return    List(Integer)
     */
    private List<Integer> randomColor(int colorListSize) {
        List<Integer> colorList = new ArrayList<>();
        if (colorListSize <= 1) {
            colorList.add(Color.parseColor("#000000"));
        }
        for (int i = 0; i < colorListSize; i++) {
            Random random = new Random();
            int num = random.nextInt(16777215);
            String hex = Integer.toHexString(num);
            if (hex.length() < 6) {
                for (int j = 0; j <= 6 - hex.length(); j++) {
                    hex = "0" + hex;
                }
            }
            Log.e("deb", "Random Color is " + hex);
            colorList.add(Color.parseColor("#" + hex));
        }
        return colorList;
    }

//    private void initializeExpandableSelector() {
//        final ExpandableSelector sizesExpandableSelector = (ExpandableSelector) findViewById(R.id.param_selector);
//        List<ExpandableItem> expandableItems = new ArrayList<ExpandableItem>();
//        expandableItems.add(new ExpandableItem("XL"));
//        expandableItems.add(new ExpandableItem("L"));
//        expandableItems.add(new ExpandableItem("M"));
//        expandableItems.add(new ExpandableItem("S"));
//        sizesExpandableSelector.showExpandableItems(expandableItems);
//        sizesExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
//            @Override public void onExpandableItemClickListener(int index, View view) {
//                switch (index) {
//                    case 1:
//                        ExpandableItem firstItem = sizesExpandableSelector.getExpandableItem(1);
//                        swipeFirstItem(1, firstItem);
//                        break;
//                    case 2:
//                        ExpandableItem secondItem = sizesExpandableSelector.getExpandableItem(2);
//                        swipeFirstItem(2, secondItem);
//                        break;
//                    case 3:
//                        ExpandableItem fourthItem = sizesExpandableSelector.getExpandableItem(3);
//                        swipeFirstItem(3, fourthItem);
//                        break;
//                    default:
//                }
//                sizesExpandableSelector.collapse();
//            }
//
//            private void swipeFirstItem(int position, ExpandableItem clickedItem) {
//                ExpandableItem firstItem = sizesExpandableSelector.getExpandableItem(0);
//                sizesExpandableSelector.updateExpandableItem(0, clickedItem);
//                sizesExpandableSelector.updateExpandableItem(position, firstItem);
//            }
//        });
//    }

}
