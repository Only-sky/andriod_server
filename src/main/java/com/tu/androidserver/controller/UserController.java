package com.tu.androidserver.controller;

import com.tu.androidserver.bean.ChatRecord;
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
    public boolean remark(@RequestParam Integer userId,@RequestParam Integer topicId, @RequestParam String content) {
        return userService.remark(userId,topicId,content);
    }

    @PostMapping("/viewComment")
    @ResponseBody
    public List<Comment> viewComment(@RequestParam Integer topicId) {
        return userService.viewComment(topicId);
    }

    @PostMapping("/viewAllTopic")
    @ResponseBody
    public List<Topic> viewAllTopic() {
        return userService.viewAllTopic();
    }

    @PostMapping("/addFriend")
    @ResponseBody
    public boolean addFriend(@RequestParam Integer senderId,@RequestParam Integer receiverId) {
        return userService.addFriend(senderId,receiverId);
    }

    @PostMapping("/displayFriendApplication")
    @ResponseBody
    public List<User> displayFriendApplication(@RequestParam Integer receiverId) {
        return userService.displayFriendApplication(receiverId);
    }

    @PostMapping("/acceptFriendApplication")
    @ResponseBody
    public boolean acceptFriendApplication(@RequestParam Integer receiverId,@RequestParam Integer senderId) {
        return userService.acceptFriendApplication(receiverId,senderId);
    }

    @PostMapping("/deleteFriend")
    @ResponseBody
    public boolean deleteFriend(@RequestParam Integer senderId,@RequestParam Integer receiverId) {
        return userService.deleteFriend(senderId,receiverId);
    }

    @PostMapping("/acceptDeleteFriendMessage")
    @ResponseBody
    public List<User> acceptDeleteFriendMessage(@RequestParam Integer receiverId) {
        return userService.acceptDeleteFriendMessage(receiverId);
    }

    @PostMapping("/displayAllFriend")
    @ResponseBody
    public List<User> displayAllFriend(@RequestParam Integer id) {
        return userService.displayAllFriend(id);
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    public boolean sendMessage(@RequestParam Integer senderId,@RequestParam Integer receiverId,@RequestParam String content) {
        return userService.sendMessage(senderId,receiverId,content);
    }

    @PostMapping("/displayAllMessage")
    @ResponseBody
    public List<ChatRecord> displayAllMessage(@RequestParam Integer senderId, @RequestParam Integer receiverId) {
        return userService.displayAllMessage(senderId,receiverId);
    }

    @PostMapping("/isUnreadMessage")
    @ResponseBody
    public List<User> isUnreadMessage(@RequestParam Integer senderId) {
        return userService.isUnreadMessage(senderId);
    }
}
