package Objects;
/**
 * Created by charlie on 2017/10/25.
 * HRV Class, 12 arguments
 */

public class HRV {
    private double RR, SD, TP, VL, LF, HF, LFP, HFP, VAR, LHR, WL;
    private String timestamp;

    public HRV(String timestamp, double rR, double sD, double tP, double vL, double lF, double hF, double lFP, double hFP, double vAR,
               double lHR, double wL) {
        super();
        RR = rR;
        SD = sD;
        TP = tP;
        VL = vL;
        LF = lF;
        HF = hF;
        LFP = lFP;
        HFP = hFP;
        VAR = vAR;
        LHR = lHR;
        WL = wL;
        this.timestamp = timestamp;
    }

    public double getRR() {
        return RR;
    }

    public void setRR(double rR) {
        RR = rR;
    }

    public double getSD() {
        return SD;
    }

    public void setSD(double sD) {
        SD = sD;
    }

    public double getTP() {
        return TP;
    }

    public void setTP(double tP) {
        TP = tP;
    }

    public double getVL() {
        return VL;
    }

    public void setVL(double vL) {
        VL = vL;
    }

    public double getLF() {
        return LF;
    }

    public void setLF(double lF) {
        LF = lF;
    }

    public double getHF() {
        return HF;
    }

    public void setHF(double hF) {
        HF = hF;
    }

    public double getLFP() {
        return LFP;
    }

    public void setLFP(double lFP) {
        LFP = lFP;
    }

    public double getHFP() {
        return HFP;
    }

    public void setHFP(double hFP) {
        HFP = hFP;
    }

    public double getVAR() {
        return VAR;
    }

    public void setVAR(double vAR) {
        VAR = vAR;
    }

    public double getLHR() {
        return LHR;
    }

    public void setLHR(double lHR) {
        LHR = lHR;
    }

    public double getWL() {
        return WL;
    }

    public void setWL(double wL) {
        WL = wL;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
