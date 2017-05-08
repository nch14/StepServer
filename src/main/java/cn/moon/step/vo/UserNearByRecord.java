package cn.moon.step.vo;

/**
 * Created by chenhaonee on 2017/5/9.
 */
public class UserNearByRecord {
    private String username;
    private double distance;
    private int counts;
    private double height;

    public UserNearByRecord() {
    }

    public UserNearByRecord(String username, double distance, int counts, double height) {
        this.username = username;
        this.distance = distance;
        this.counts = counts;
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
