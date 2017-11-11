package com.yuda.dto;

import lombok.Data;

/**
 * @auther yuda
 * Create on 2017/11/8 16:17.
 * Project_name :   seckill
 * Package_name :   com.yuda.dto
 * Description  :   VO 封装所有数据结果
 */
@Data
public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
