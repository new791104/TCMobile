package Global;

import Objects.disScrollingPager;
import Objects.Dummy;
import Objects.HRV;
import Objects.SPO2;
import Objects.tableList;
import Objects.userQuery;

/**
 * Created by charlie on 2017/10/25.
 */

public class GV {
    public static userQuery query = new userQuery();
    public static int pagePosition = 0;
    public static tableList tablelist = new Dummy().getDummyTable();
    public static tableList<HRV> hrvTable;
    public static tableList<SPO2> spo2Table;
    public static disScrollingPager mViewPager;
    public static int totalPage = 4;

    public static void checkDataType() {
        if (tablelist.getType().equals("HRV")) {

        }
    }
}
