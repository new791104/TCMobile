package Objects;

/**
 * Created by cminer on 2018/3/1.
 */

public class userUpdate {
    private String userID, deviceID, dataType;

    public userUpdate() {
        this.userID = null;
        this.deviceID = null;
        this.dataType = null;
    }

    public userUpdate(String userID, String deviceID, String dataType) {
        this.userID = userID;
        this.deviceID = deviceID;
        this.dataType = dataType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
