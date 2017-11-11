package com.yuda.dao;

import com.yuda.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @auther yuda
 * Create on 2017/11/7 19:43.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   TODO
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSucceccKilled() throws Exception {
        int i = successKilledDao.insertSucceccKilled(1001L, 13972218121L);
        System.out.println("状态码::" + i);
    }

    /**
     * 连接查询出来的
     * SuccessKilled
     * {seckillId=1000, userPhone=13972218121, state=-1, createTime=Tue Nov 07 19:47:13 CST 2017,
     * seckill=Seckill{seckillId=1000, name='1000元秒杀飞机', number=98, startTime=Tue Nov 07 19:42:52 CST 2017, endTime=Thu Nov 09 00:00:00 CST 2017, createTime=Tue Nov 07 16:10:00 CST 2017}}
     *
     * @throws Exception
     */
    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1001L, 13972218121L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

    @Test
    public void demo1() throws Exception {


    }
}