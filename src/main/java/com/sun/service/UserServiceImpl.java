package com.sun.service;

import com.sun.dao.UserMapper;
import com.sun.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service 使用spring-service.xml配置文件注入类和属性
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //登录验证
    public String loginCheck(String userName) {
        String passWord = userMapper.loginCheck(userName);
        return passWord;
    }
    //注册用户
    public int registeredUser(User user) {
        return userMapper.registeredUser(user);
    }

    public User queryUser(String userName) {
        User user = userMapper.queryUser(userName);
        return user;
    }
}
