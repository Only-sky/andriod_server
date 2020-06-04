package com.tu.androidserver.controller;

import com.tu.androidserver.bean.User;
import com.tu.androidserver.service.MyService;
import com.tu.androidserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Jordan_tu
 * @date 2020/6/2
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return userService.login(email, password);
    }

    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestParam(value = "name")String name, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        User user=new User(name,email,password);
        return userService.register(user);
    }
}
