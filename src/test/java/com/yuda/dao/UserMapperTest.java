package com.yuda.dao;

import com.yuda.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/10 10:36.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() throws Exception {
        User user = new User();
        user.setUsername("胡帅");
        user.setPassword("胡帅666");
        userMapper.insert(user);
    }

    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    public void insertList() throws Exception {
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setU_id(3);
        user.setUsername("改后的名字");
        userMapper.update(user);
    }

    @Test
    public void listAllUser() throws Exception {
        List<User> users = userMapper.listAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}