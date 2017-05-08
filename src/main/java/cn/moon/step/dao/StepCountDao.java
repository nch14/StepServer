package cn.moon.step.dao;

import cn.moon.step.model.StepCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by chenhaonee on 2017/5/8.
 */
public interface StepCountDao extends JpaRepository<StepCount, Long> {

    List<StepCount> findByThisTimestampBetweenAndSumIsFalseAndUsername(Timestamp from, Timestamp to,String username);

    StepCount findByUsernameAndSumIsTrueAndThisTimestamp(String username,Timestamp timestamp);

   //List<StepCount> findByUsernameAndSumIsFalseAndThisTimestampBetween(String username, Timestamp timestamp);

}
