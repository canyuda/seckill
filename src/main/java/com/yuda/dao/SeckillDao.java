package com.yuda.dao;

import com.yuda.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/7 16:37.
 * Project_name :   seckill
 * Package_name :   com.yuda.dao
 * Description  :   秒杀列表Dao
 */
public interface SeckillDao {
    /**
     * 减库存
     *
     * @param seckillId 商品id
     * @param killTime  秒杀时间
     * @return 如果影响的行数>1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀库存对象
     *
     * @param seckillId 商品id
     * @return 返回查询出的商品
     */
    Seckill queryById(long seckillId);

    /**
     * 分页查询
     *
     * @param offset 偏移量
     * @param limit  几条数据
     * @return 商品列表
     */
    //注解用于指定名字
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
