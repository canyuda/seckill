package com.yuda.dao;

import com.yuda.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @auther yuda
 * Create on 2017/11/9 21:49.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Test
    public void getSeckill() throws Exception {
        Seckill seckill = new Seckill();
        seckill.setSeckillId(10001);
        seckill.setName("redis测试");
        seckill.setNumber(100);
        seckill.setCreateTime(new Date());
        seckill.setStartTime(new Date(2017, 10, 10));
        seckill.setEndTime(new Date(2018, 11, 11));
        redisDao.setSeckill(seckill);
    }

    @Test
    public void setSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(10001);
        System.out.println(seckill);
    }

}