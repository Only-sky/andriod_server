package com.tu.androidserver.controller;

import com.tu.androidserver.bean.ChatRecord;
import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import com.tu.androidserver.bean.User;
import com.tu.androidserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public int login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return userService.login(email, password);
    }

    @PostMapping("/displayUserInfo")
    @ResponseBody
    public User displayUserInfo(String id) {
        return userService.displayUserInfo(Integer.parseInt(id));
    }

    @PostMapping("/changeUserInfo")
    @ResponseBody
    public boolean changeUserInfo(@RequestParam(value = "id") String id,@RequestParam(value = "name")String name,@RequestParam(value = "password") String password,
                                  @RequestParam(value = "sex")String sex, @RequestParam(value = "phone")String phone,
                                  @RequestParam(value = "address")String address) {
        User user = new User(Integer.valueOf(id),name,"",password,sex,phone,address);
        return userService.changeUserInfo(user);
    }

    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestParam(value = "name")String name, @RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password, @RequestParam(value = "sex")String sex,
                        @RequestParam(value = "phone")String phone,@RequestParam(value = "address")String address,
                        @RequestParam(value = "captcha") String captcha){
        User user=new User(name,email,password,sex,phone,address);
        return userService.register(user,captcha);
    }

    @PostMapping("/registerCaptcha")
    @ResponseBody
    public int sendCaptcha(@RequestParam String email){
        return userService.sendCaptcha(email);
    }

    @PostMapping("/postTopic")
    @ResponseBody
    public boolean postTopic(@RequestParam String userId,@RequestParam String title,@RequestParam String content) {
        return userService.postTopic(Integer.valueOf(userId),title,content);
    }

    @PostMapping("/remark")
    @ResponseBody
    public boolean remark(@RequestParam String userId,@RequestParam String topicId, @RequestParam String content) {
        return userService.remark(Integer.valueOf(userId),Integer.valueOf(topicId),content);
    }

    @PostMapping("/viewComment")
    @ResponseBody
    public List<Comment> viewComment(@RequestParam String topicId) {
        return userService.viewComment(Integer.valueOf(topicId));
    }

    @PostMapping("/viewAllTopic")
    @ResponseBody
    public List<Topic> viewAllTopic() {
        return userService.viewAllTopic();
    }

    @PostMapping("/addFriend")
    @ResponseBody
    public int addFriend(@RequestParam String senderId,@RequestParam String receiverEmail) {
        return userService.addFriend(Integer.valueOf(senderId),receiverEmail);
    }

    @PostMapping("/displayFriendApplication")
    @ResponseBody
    public List<User> displayFriendApplication(@RequestParam String receiverId) {
        return userService.displayFriendApplication(Integer.valueOf(receiverId));
    }

    @PostMapping("/acceptFriendApplication")
    @ResponseBody
    public boolean acceptFriendApplication(@RequestParam String receiverId,@RequestParam String senderId) {
        return userService.acceptFriendApplication(Integer.valueOf(receiverId),Integer.valueOf(senderId));
    }

    @PostMapping("/refuseFriendApplication")
    @ResponseBody
    public boolean refuseFriendApplication(@RequestParam String receiverId,@RequestParam String senderId) {
        return userService.refuseFriendApplication(Integer.valueOf(receiverId),Integer.valueOf(senderId));
    }

    @PostMapping("/deleteFriend")
    @ResponseBody
    public boolean deleteFriend(@RequestParam String senderId,@RequestParam String receiverId) {
        return userService.deleteFriend(Integer.valueOf(senderId),Integer.valueOf(receiverId));
    }

    @PostMapping("/acceptDeleteFriendMessage")
    @ResponseBody
    public List<User> acceptDeleteFriendMessage(@RequestParam String receiverId) {
        return userService.acceptDeleteFriendMessage(Integer.valueOf(receiverId));
    }

    @PostMapping("/displayAllFriend")
    @ResponseBody
    public List<User> displayAllFriend(@RequestParam String id) {
        return userService.displayAllFriend(Integer.valueOf(id));
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    public boolean sendMessage(@RequestParam String senderId,@RequestParam String receiverId,@RequestParam String content) {
        return userService.sendMessage(Integer.valueOf(senderId),Integer.valueOf(receiverId),content);
    }

    @PostMapping("/displayAllMessage")
    @ResponseBody
    public List<ChatRecord> displayAllMessage(@RequestParam String senderId, @RequestParam String receiverId) {
        return userService.displayAllMessage(Integer.valueOf(senderId),Integer.valueOf(receiverId));
    }

    @PostMapping("/isUnreadMessage")
    @ResponseBody
    public List<User> isUnreadMessage(@RequestParam String senderId) {
        return userService.isUnreadMessage(Integer.valueOf(senderId));
    }

    @GetMapping("/test")
    @ResponseBody
    public String testConnect(HttpServletRequest request) {
        return "test";
    }
}
