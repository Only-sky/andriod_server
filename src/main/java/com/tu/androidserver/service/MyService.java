package com.tu.androidserver.service;

import com.tu.androidserver.bean.User;
import com.tu.androidserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jordan_tu
 * @date 2020/6/2
 */
@org.springframework.stereotype.Service
public class MyService {
    @Autowired
    UserMapper userMapper;

    public boolean login(String email,String password){
        User user = userMapper.getUserByEmail(email);
        if( user!=null  && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean register(String email,String password){ //预先判断邮箱是否注册
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userMapper.getUserByEmail(email) == null && userMapper.insertUser(user) >= 1;
    }
}
