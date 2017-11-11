package com.yuda.serivce.impl;

import com.yuda.dto.Exposer;
import com.yuda.entity.Seckill;
import com.yuda.serivce.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/8 15:11.
 * Project_name :   seckill
 * Package_name :   com.yuda.serivce.impl
 * Description  :   TODO
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill : seckillList) {
            System.out.println(seckill);
        }
    }

    @Test
    public void getById() throws Exception {
        Seckill byId = seckillService.getById(1001L);
        System.out.println(byId);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1002L);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        seckillService.executeSeckill(1000L, 15623060836L, "2b6f1a58629bbdebc3f9e3f614777d13d");
    }

}