package com.yuda.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.yuda.entity.User;


public interface UserMapper {

    int insert(@Param("user") User user);

    int insertSelective(@Param("user") User user);

    int insertList(@Param("users") List<User> users);

    int update(@Param("user") User user);

    List<User> listAllUser();

}
