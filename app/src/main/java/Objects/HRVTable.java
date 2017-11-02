package Objects;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/11/1.
 */

public class HRVTable {
    private LocalDate edate;
    private ArrayList<HRV> hrvArray;
    public HRVTable(LocalDate edate, ArrayList<HRV> hrvArray) {
        super();
        this.edate = edate;
        this.hrvArray = hrvArray;
    }
    public LocalDate getEdate() {
        return edate;
    }
    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }
    public ArrayList<HRV> getHrvArray() {
        return hrvArray;
    }
    public void setHrvArray(ArrayList<HRV> hrvArray) {
        this.hrvArray = hrvArray;
    }


}

