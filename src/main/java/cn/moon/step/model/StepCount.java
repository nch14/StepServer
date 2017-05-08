package cn.moon.step.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by chenhaonee on 2017/5/8.
 */
@Entity
public class StepCount {
    @Id
    @GeneratedValue
    private long id;
    private Timestamp thisTimestamp;
    private int thisCounts;
    private String username;
    private boolean isSum =false;

    public StepCount() {
    }

    public StepCount(Timestamp thisTimestamp, int thisCounts, String username) {
        this.thisTimestamp = thisTimestamp;
        this.thisCounts = thisCounts;
        this.username = username;
    }

    public StepCount(Date thisTimestamp, int thisCounts, String username) {
        this.thisTimestamp = new Timestamp(thisTimestamp.getTime());
        this.thisCounts = thisCounts;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getThisTimestamp() {
        return thisTimestamp;
    }

    public void setThisTimestamp(Timestamp thisTimestamp) {
        this.thisTimestamp = thisTimestamp;
    }

    public int getThisCounts() {
        return thisCounts;
    }

    public void setThisCounts(int thisCounts) {
        this.thisCounts = thisCounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSum() {
        return isSum;
    }

    public void setSum(boolean sum) {
        isSum = sum;
    }
}
