package cn.moon.step.model;

import javax.persistence.Entity;

/**
 * Created by chenhaonee on 2017/5/8.
 */
@Entity
public class User {
    private String username;
    private String password;

    private double xValue;
    private double yValue;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, double xValue, double yValue) {
        this.username = username;
        this.password = password;
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }
}
