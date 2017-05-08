package cn.moon.step.controller;

import cn.moon.step.dao.StepCountDao;
import cn.moon.step.dao.UserDao;
import cn.moon.step.model.StepCount;
import cn.moon.step.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by chenhaonee on 2017/5/8.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StepCountDao stepCountDao;

    @RequestMapping(value = "/login")
    public boolean login(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        User user = userDao.findOne(username);
        if (user != null && user.getPassword().equals(password))
            return true;
        else
            return false;
    }

    @RequestMapping(value = "/join")
    public boolean join(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        User user = new User(username, password);
        userDao.save(user);
        return true;
    }

    @RequestMapping(value = "/data")
    public void todaySteps(@RequestParam(value = "username") String username,
                           @RequestParam(value = "count") int count) {
        StepCount stepCount = new StepCount(new Timestamp(System.currentTimeMillis()), count, username);
        stepCountDao.save(stepCount);
    }
}
