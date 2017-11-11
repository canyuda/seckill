package com.yuda.exception;

/**
 * @auther yuda
 * Create on 2017/11/8 11:55.
 * Project_name :   seckill
 * Package_name :   com.yuda.exception
 * Description  :   TODO
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String seckill_data_rewrite) {
        super(seckill_data_rewrite);
    }
}
