package com.tu.androidserver.controller;

import com.tu.androidserver.bean.User;
import com.tu.androidserver.service.MyService;
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
    MyService service;

//    @GetMapping("/login/{email}/{password}")
//    @ResponseBody
//    public boolean login(@PathVariable String email,@PathVariable String password){
//        return service.isValide(email, password);
//    }

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam(value = "email", required = true) String email,@RequestParam(value = "password", required = true) String password){
        return service.login(email, password);
    }

    @PostMapping("/register")
    @ResponseBody
    public boolean register(@RequestParam(value = "email", required = true) String email,@RequestParam(value = "password", required = true) String password){
        return service.register(email,password);
    }
}
