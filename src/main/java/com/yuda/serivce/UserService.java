package com.yuda.serivce;

import java.util.List;
import com.yuda.entity.User;
public interface UserService{

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int update(User user);
}
