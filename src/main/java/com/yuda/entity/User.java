package com.yuda.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @auther yuda
 * Create on 2017/11/10 10:31.
 * Project_name :   seckill
 * Package_name :   com.yuda.entity
 * Description  :   TODO
 */
@Data
public class User {
    private Integer u_id;
    private String username;
    private String password;
    private Date birthday;
    private Date createTime;
}
