package edu.northeastern.kinnarkansara.finalprojectnbweb.model;

/**
 *
 * @author kinnar
 */
public class DailyDelayTuple {

    private String yyyymmdd = "";
    private int flightsCount = 0;
    private int delayedFlightsCount = 0;
    private double delayPercentage = 0.0;
    private int canceledFlightsCount = 0;
    private double canceledPercentage = 0.0;

    public String getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(String yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

    public void setFlightsCount(int flightsCount) {
        this.flightsCount = flightsCount;
    }

    public int getDelayedFlightsCount() {
        return delayedFlightsCount;
    }

    public void setDelayedFlightsCount(int delayedFlightsCount) {
        this.delayedFlightsCount = delayedFlightsCount;
    }

    public double getDelayPercentage() {
        return delayPercentage;
    }

    public void setDelayPercentage(double delayPercentage) {
        this.delayPercentage = delayPercentage;
    }

    public int getCanceledFlightsCount() {
        return canceledFlightsCount;
    }

    public void setCanceledFlightsCount(int canceledFlightsCount) {
        this.canceledFlightsCount = canceledFlightsCount;
    }

    public double getCanceledPercentage() {
        return canceledPercentage;
    }

    public void setCanceledPercentage(double canceledPercentage) {
        this.canceledPercentage = canceledPercentage;
    }

    @Override
    public String toString() {
        return this.yyyymmdd + ", " + this.flightsCount + ", " + this.delayedFlightsCount + ", " + this.delayPercentage
                + ", " + this.canceledFlightsCount + ", " + this.canceledPercentage;
    }
}
