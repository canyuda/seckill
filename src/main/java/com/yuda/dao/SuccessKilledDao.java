package com.yuda.dao;

import com.yuda.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @auther yuda
 * Create on 2017/11/7 16:40.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   购买明细接口
 */
public interface SuccessKilledDao {
    /**
     * 购买明细添加,可过滤重复
     *
     * @param seckillId (使用联合主键来防止多买)
     * @param userPhone 用户手机号用于识别不同用户
     * @return 插入的结果数量
     */
    int insertSucceccKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    /**
     * 根据id查询购买明细并携带秒杀产品对象
     *
     * @param seckillId 商品
     * @return 明细并携带了商品
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
