package com.yuda.dto;

import com.yuda.entity.SuccessKilled;
import com.yuda.enums.SeckillStatEnum;
import lombok.Data;

/**
 * @auther yuda
 * Create on 2017/11/8 10:36.
 * Project_name :   seckill
 * Package_name :   com.yuda.dto
 * Description  :   秒杀执行后的结果
 */
@Data
public class SeckillExecution {

    //成功失败
    private long seckillId;

    private int state;

    //状态
    private String stateInfo;

    //秒杀对象
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateinfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateinfo();
    }
}
