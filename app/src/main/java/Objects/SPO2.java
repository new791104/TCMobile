package Objects;

/**
 * Created by charlie on 2017/11/26.
 */

public class SPO2 {
    private double SPO2,PULSE,PA;
    private String timestamp;

    public SPO2(String timestamp, double Spo2, double Pulse, double Pa) {
        super();
        SPO2 = Spo2;
        PULSE = Pulse;
        PA = Pa;
        this.timestamp = timestamp;
    }

    public double getSPO2() {
        return SPO2;
    }

    public void setSPO2(double sPO2) {
        SPO2 = sPO2;
    }

    public double getPULSE() {
        return PULSE;
    }

    public void setPULSE(double pULSE) {
        PULSE = pULSE;
    }

    public double getPA() {
        return PA;
    }

    public void setPA(double pA) {
        PA = pA;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
