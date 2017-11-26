package Global;

import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import Objects.HRV;
import Objects.SPO2;
import Objects.dataTable;
import Objects.userQuery;

/**
 * Created by charlie on 2017/10/25.
 */

public class GV {
    public static userQuery query = new userQuery();
    public static int pagePosition = 0;
    public static ArrayList<dataTable<HRV>> hrvTable;
    public static ArrayList<dataTable<SPO2>> sp02Table;
    public static ViewPager mViewPager;
    public static int totalPage = 4;
}
