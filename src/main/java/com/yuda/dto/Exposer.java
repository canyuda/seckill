package com.yuda.dto;

import com.yuda.entity.Seckill;
import lombok.Data;

/**
 * @auther yuda
 * Create on 2017/11/8 10:28.
 * Project_name :   seckill
 * Package_name :   com.yuda.dto
 * Description  :   暴露秒杀地址DTO
 */
@Data
public class Exposer {

    //是否开启
    private boolean exposed;
    //加密的url
    private String md5;
    //ID
    private long seckillId;
    //系统当前时间
    private long now;
    //开始时间
    private long start;
    //结束时间
    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

}
