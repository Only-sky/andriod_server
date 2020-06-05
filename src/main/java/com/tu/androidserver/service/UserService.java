package com.tu.androidserver.service;

import com.sun.istack.internal.NotNull;
import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import com.tu.androidserver.bean.User;
import com.tu.androidserver.mapper.CommentMapper;
import com.tu.androidserver.mapper.TopicMapper;
import com.tu.androidserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;

    /**
     * 用户注册
     * @return
     * -1:邮箱重复
     * 0:注册失败
     * */
    public int register(@NotNull User user) {
        if(userMapper.getUserByEmail(user.getEmail())!=null) {  //邮箱重复
            return -1;
        }
        int num=0;
        if((num=userMapper.insertUser(user))>0) {
            return num;
        }
        return 0;
    }

    /**
     * 登陆
     * */
    public boolean login(@NotNull String email,@NotNull String password) {
        User loginUser=userMapper.getUserByEmail(email);
        if(loginUser==null) {
            return false;
        }
        if(loginUser.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * 评论
     * */
    public boolean remark(@NotNull Integer userId,@NotNull Integer topicId,String content) {
        Comment comment=new Comment(content,userId,topicId,new Timestamp(System.currentTimeMillis()));
        if(commentMapper.insertComment(comment)==1) {
            return true;
        }
        return false;
    }

    /**
     * 发表话题
     * */
    public boolean postTopic(Integer userId,String title,String content) {
        Topic topic=new Topic(title,content,new Timestamp(System.currentTimeMillis()),userId);
        if(topicMapper.insertTopic(topic)==1) {
            return true;
        }
        return false;
    }

    /**
     * 查看某一话题下的所有评论
     * */
    public List<Comment> viewComment(Integer topicId) {
        return commentMapper.getCommentByTopicId(topicId);
    }

    /**
     * 查看所有话题
     * */
    public List<Topic> viewAllTopic() {
        return topicMapper.getAllTopic();
    }
}
