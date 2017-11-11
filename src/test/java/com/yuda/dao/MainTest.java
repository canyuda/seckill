package com.yuda.dao;

import com.yuda.dao.SeckillDao;
import com.yuda.entity.Seckill;
import com.yuda.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/7 19:00.
 * Project_name :   seckill
 * Package_name :   PACKAGE_NAME
 * Description  :   TODO
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class MainTest {

    @Autowired
    private SeckillDao seckillDao;
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void demo1() {
        Seckill seckill = seckillDao.queryById(1000);
        System.out.println("灿宇达" + seckill);
    }

    @Test
    public void demo2() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 5);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void demo3() throws Exception {
        Date killTime = new Date();
        int i = seckillDao.reduceNumber(1000, killTime);
        System.out.println("状态码::" + i);
    }

    @Test
    public void insertSucceccKilled() throws Exception {
        int i = successKilledDao.insertSucceccKilled(1001L, 13972218121L);
        System.out.println("状态码::" + i);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1001L, 13972218121L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}
