package com.tu.androidserver.controller;

import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import com.tu.androidserver.bean.User;
import com.tu.androidserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public int register(@RequestParam(value = "name")String name, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
                        @RequestParam(value = "sex")String sex,@RequestParam(value = "phone")String phone,@RequestParam(value = "address")String address){
        User user=new User(name,email,password,sex,phone,address);
        return userService.register(user);
    }

    @PostMapping("/postTopic")
    @ResponseBody
    public boolean postTopic(@RequestParam Integer userId,@RequestParam String title,@RequestParam String content) {
        return userService.postTopic(userId,title,content);
    }

    @PostMapping("/remark")
    @ResponseBody
    public boolean remark(Integer userId,Integer topicId, String content) {
        return userService.remark(userId,topicId,content);
    }

    @PostMapping("/viewComment")
    @ResponseBody
    public List<Comment> viewComment(Integer topicId) {
        return userService.viewComment(topicId);
    }

    @PostMapping("/viewAllTopic")
    @ResponseBody
    public List<Topic> viewAllTopic() {
        return userService.viewAllTopic();
    }
}
