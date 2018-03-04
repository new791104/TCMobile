package Objects;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/11/1.
 */

public class dataTable<T> {
    private LocalDate edate;
    private ArrayList<T> arrayList;

    public dataTable(LocalDate edate, ArrayList<T> arraylist) {
        // TODO Auto-generated constructor stub
        super();
        this.edate = edate;
        this.arrayList = arraylist;
    }
    public LocalDate getEdate() {
        return edate;
    }
    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }
    public ArrayList<T> getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList<T> arraylist) {
        this.arrayList = arraylist;
    }


}

