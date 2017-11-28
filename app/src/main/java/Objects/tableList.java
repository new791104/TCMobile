package Objects;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/11/27.
 */

public class tableList<T> {
    private ArrayList<dataTable<T>> datatable;
    private String type;
    private String[] dataFields;

    public tableList(ArrayList<dataTable<T>> dataSection, String type, String[] fields) {
        super();
        this.datatable = dataSection;
        this.type = type;
        this.dataFields = fields;
    }

    public ArrayList<dataTable<T>> getDatatable() {
        return datatable;
    }

    public void setDatatable(ArrayList<dataTable<T>> datatable) {
        this.datatable = datatable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getDataFields() {
        return dataFields;
    }

    public void setDataFields(String[] fields) {
        this.dataFields = fields;
    }


}

