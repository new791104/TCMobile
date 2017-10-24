package Objects;

/**
 * Created by charlie on 2017/10/25.
 */

public class HRV {
    private double HR, VAR, PA, TE, BAT;

    public HRV(double hr, double v, double pa, double te, double bat) {
        HR = hr;
        VAR = v;
        PA = pa;
        TE = te;
        BAT = bat;
    }
    public double getHR() {
        return HR;
    }

    public void setHR(double hR) {
        HR = hR;
    }

    public double getVAR() {
        return VAR;
    }

    public void setVAR(double v) {
        VAR = v;
    }

    public double getPA() {
        return PA;
    }

    public void setPA(double pA) {
        PA = pA;
    }

    public double getTE() {
        return TE;
    }

    public void setTE(double tE) {
        TE = tE;
    }

    public double getBAT() {
        return BAT;
    }

    public void setBAT(double bat) {
        BAT = bat;
    }

}
