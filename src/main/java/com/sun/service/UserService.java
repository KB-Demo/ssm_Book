package com.sun.service;


import com.sun.pojo.User;

public interface UserService {

    //登录验证
    String loginCheck(String userName);

    //注册用户
    int registeredUser(User user);

    //查询用户
    User queryUser(String userName);
}
