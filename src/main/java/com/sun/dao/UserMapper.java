package com.sun.dao;

import com.sun.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //登录验证
    String loginCheck(@Param("userName") String username);

    //注册用户
    int registeredUser(User user);

    //查询用户
    User queryUser(@Param("userName") String username);
}
