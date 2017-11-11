package com.yuda.serivce;

import com.yuda.dto.Exposer;
import com.yuda.dto.SeckillExecution;
import com.yuda.entity.Seckill;
import com.yuda.exception.RepeatKillException;
import com.yuda.exception.SeckillCloseException;
import com.yuda.exception.SeckillException;

import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/8 0:25.
 * Project_name :   seckill
 * Package_name :   com.yuda.serivce
 * Description  :   TODO
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    //执行秒杀
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException;
}
