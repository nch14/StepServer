package cn.moon.step.dao;

import cn.moon.step.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chenhaonee on 2017/5/8.
 */
public interface UserDao extends JpaRepository<User,String> {
}
