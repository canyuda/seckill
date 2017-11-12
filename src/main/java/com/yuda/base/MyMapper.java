package com.yuda.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @auther yuda
 * Create on 2017/11/12 10:11.
 * Project_name :   seckill
 * Package_name :   com.yuda.base
 * Description  :   TODO
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
