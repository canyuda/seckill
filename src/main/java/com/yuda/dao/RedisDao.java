package com.yuda.dao;

import com.yuda.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @auther yuda
 * Create on 2017/11/9 20:34.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   TODO
 */
@Repository
public class RedisDao {
    @Autowired
    private RedisTemplate<String, Seckill> redisTemplate;


    public Seckill getSeckill(long seckillId) {

        if (1 == 1) {
            return null;
        }

        Seckill seckill = null;
        String key = "seckill:" + seckillId;
        seckill = redisTemplate.boundValueOps(key).get();
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        if (1 == 1) {
            return;
        }
        String key = "seckill:" + seckill.getSeckillId();
        redisTemplate.boundValueOps(key).set(seckill, 1, TimeUnit.HOURS);
    }
}
