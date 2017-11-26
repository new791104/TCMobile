package Objects;

import org.joda.time.LocalDate;

/**
 * Created by charlie on 2017/11/1.
 */

public class userQuery {
    private String userID, deviceID, dataType;
    private LocalDate dataStartDate, dataEndDate;

    public userQuery() {
        super();
        this.userID = "F123456789";
        this.deviceID = null;
        this.dataType = null;
        this.dataStartDate = new LocalDate("2017-10-12");
        this.dataEndDate = new LocalDate("2017-10-13");
    }

    public userQuery(String userID, String deviceID, String dataType,LocalDate dataStartDate, LocalDate dataEndDate) {
        super();
        this.userID = userID;
        this.deviceID = deviceID;
        this.dataType = dataType;
        this.dataStartDate = dataStartDate;
        this.dataEndDate = dataEndDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public LocalDate getDataStartDate() {
        return dataStartDate;
    }
    public void setDataStartDate(LocalDate dataStartDate) {
        this.dataStartDate = dataStartDate;
    }
    public LocalDate getDataEndDate() {
        return dataEndDate;
    }
    public void setDataEndDate(LocalDate dataEndDate) {
        this.dataEndDate = dataEndDate;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


}
