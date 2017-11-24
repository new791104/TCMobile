package Objects;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/11/1.
 */

public class dataTable <T>{
    private LocalDate edate;
    private ArrayList<T> Array;
    public dataTable(LocalDate edate, ArrayList<T> hrvArray) {
        super();
        this.edate = edate;
        this.Array = Array;
    }
    public LocalDate getEdate() {
        return edate;
    }
    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }
    public ArrayList<T> getHrvArray() {
        return Array;
    }
    public void setHrvArray(ArrayList<T> hrvArray) {
        this.Array = hrvArray;
    }

}

