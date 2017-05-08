package cn.moon.step.controller;

import cn.moon.step.dao.StepCountDao;
import cn.moon.step.dao.UserDao;
import cn.moon.step.model.StepCount;
import cn.moon.step.model.User;
import cn.moon.step.util.GEO;
import cn.moon.step.util.TimeUtil;
import cn.moon.step.vo.StepRecord;
import cn.moon.step.vo.UserNearByRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "xValue") double xValue,
                         @RequestParam(value = "yValue") double yValue) {
        User user = userDao.findOne(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setxValue(xValue);
            user.setyValue(yValue);
            userDao.save(user);
            return true;
        } else
            return false;
    }

    @RequestMapping(value = "/join")
    public boolean join(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        User user = new User(username, password);
        userDao.save(user);
        return true;
    }

    @RequestMapping(value = "/data/today", method = RequestMethod.POST)
    public void todaySteps(@RequestParam(value = "username") String username,
                           @RequestParam(value = "count") int count) {
        StepCount stepCount = new StepCount(new Timestamp(System.currentTimeMillis()), count, username);
        stepCount.setSum(true);
        stepCountDao.save(stepCount);
    }

    @RequestMapping(value = "/data/hour", method = RequestMethod.POST)
    public void thisHourSteps(@RequestParam(value = "username") String username,
                              @RequestParam(value = "records") List<StepRecord> records) {
        for (StepRecord record : records) {
            StepCount stepCount = new StepCount(record.getTimestamp(), record.getCount(), username);
            stepCountDao.save(stepCount);
        }
    }

    @RequestMapping(value = "/data/today", method = RequestMethod.GET)
    public int todaySteps(@RequestParam(value = "username") String username) {
        List<StepCount> counts = stepCountDao.findByThisTimestampBetweenAndSumIsFalseAndUsername(
                new Timestamp(TimeUtil.getNowInDate().getTime()),
                new Timestamp(System.currentTimeMillis()),
                username);
        int count = counts.stream().mapToInt(StepCount::getThisCounts).reduce(0, Integer::sum);
        return count;
    }

    @RequestMapping(value = "/data")
    public List<StepCount> getSteps(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "from") Date from,
                                    @RequestParam(value = "to") Date to) {
        return stepCountDao.findByThisTimestampBetweenAndSumIsFalseAndUsername(new Timestamp(from.getTime()), new Timestamp(to.getTime()), username);
    }

    @RequestMapping(value = "/data/near")
    public List<UserNearByRecord> nearBy(@RequestParam(value = "username") String username) {
        User me = userDao.findOne(username);
        double xValue = me.getxValue();
        double yValue = me.getyValue();
        List<UserNearByRecord> records = userDao.findAll().
                stream()
                .map(user -> {
                    double distance = GEO.GetDistance(xValue, yValue, user.getxValue(), user.getyValue());
                    if (distance > 5000)
                        return null;
                    else {
                        List<StepCount> stepCounts = stepCountDao.findByThisTimestampBetweenAndSumIsFalseAndUsername(new Timestamp(TimeUtil.getNowInDate().getTime()), new Timestamp(System.currentTimeMillis()), user.getUsername());
                        int count = stepCounts.stream().mapToInt(StepCount::getThisCounts).reduce(0, Integer::sum);
                        return new UserNearByRecord(username, distance, count, 0L);
                    }
                }).filter(userNearByRecord -> userNearByRecord != null)
                .collect(Collectors.toList());
        return records;
    }

}
