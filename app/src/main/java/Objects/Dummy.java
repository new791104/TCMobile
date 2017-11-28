package Objects;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/11/24.
 */

public class Dummy {

    private tableList dummyTable;

    public Dummy() {
        HRV dummyHRV = new HRV("0000-01-01 00:00:00", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        ArrayList<HRV> dummyHRV_array = new ArrayList<HRV>();
        dummyHRV_array.add(dummyHRV);
        dataTable<HRV> dummyHRV_table = new dataTable(new LocalDate("0000-01-01"), dummyHRV_array);
        ArrayList<dataTable> dummyHRV_table_Array = new ArrayList<dataTable>();
        dummyHRV_table_Array.add(dummyHRV_table);
        dummyTable = new tableList(dummyHRV_table_Array, "HRV", new String[]{"No Data"});
    }

    public tableList getDummyTable() {
        return dummyTable;
    }

}
