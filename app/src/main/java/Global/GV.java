package Global;

import android.support.v7.widget.RecyclerView;

import org.joda.time.LocalDate;

import Objects.disScrollingPager;
import Objects.Dummy;
import Objects.HRV;
import Objects.SPO2;
import Objects.tableList;
import Objects.userQuery;
import Objects.userUpdate;

/**
 * Created by charlie on 2017/10/25.
 */

public class GV {
    private static LocalDate default_startDate = new LocalDate("2017-10-1");
    private static LocalDate default_endDate = new LocalDate("2017-12-31");

    public static userQuery query_list = new userQuery("F123456789", null, null, default_startDate, default_endDate);
    public static userUpdate update_list = new userUpdate();
    public static int pagePosition = 0;
    public static tableList tablelist = new Dummy().getDummyTable();
    public static tableList<HRV> hrvTable = new Dummy().getDummyTable();
    public static tableList<SPO2> spo2Table = new Dummy().getDummyTable();
    public static disScrollingPager mViewPager;

}
