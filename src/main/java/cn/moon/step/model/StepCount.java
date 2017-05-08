package cn.moon.step.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by chenhaonee on 2017/5/8.
 */
@Entity
public class StepCount {
    @Id
    @GeneratedValue
    private long id;
    private Timestamp timestamp;
    private int count;
    private String username;

    public StepCount() {
    }

    public StepCount(Timestamp timestamp, int count, String username) {
        this.timestamp = timestamp;
        this.count = count;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
