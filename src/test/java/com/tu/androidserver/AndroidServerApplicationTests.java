package com.tu.androidserver;

import com.tu.androidserver.bean.ChatRecord;
import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import com.tu.androidserver.bean.User;
import com.tu.androidserver.mapper.CommentMapper;
import com.tu.androidserver.mapper.TopicMapper;
import com.tu.androidserver.mapper.UserMapper;
import com.tu.androidserver.service.EmailUntil;
import com.tu.androidserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class AndroidServerApplicationTests {
//    @Autowired
//    UserMapper userMapper;
//    @Autowired
//    TopicMapper topicMapper;
//    @Autowired
//    CommentMapper commentMapper;
//    @Autowired
//    UserService userService;
//
//    @Test
//    void user() throws SQLException {
//        userMapper.deleteUser(1);
////        User user = new User("hty","2784216955@qq.com","123456","男","18118278807","南京");
////        System.out.println(userMapper.insertUser(user));
////        System.out.println(userMapper.getUserById(1).getEmail());
//    }
//
//    @Test
//    public void topic() {
////        Topic topic = new Topic("测试标题1","测试内容2",new Date(System.currentTimeMillis()),1);
////        System.out.println(topicMapper.insertTopic(topic));
//
//
//        for(Topic t:topicMapper.getTopicByUserId(1)) {
//            System.out.println(t.getTime().toString());
//        }
//    }
//
//
//    @Test
//    public void comment() {
////        Comment comment=new Comment("测试评论1",1,2,new Timestamp(System.currentTimeMillis()));
////        commentMapper.insertTopic(comment);
//
//        for(Comment comment:commentMapper.getCommentByUserId(1)) {
//            System.out.println(comment.getContent());
//        }
//
//    }
//
//    @Test
//    public void friend() {
//        System.out.println(userService.addFriend(1, 2));
//        System.out.println(userService.acceptFriendApplication(2, 1));
//        List<User> users=userService.displayFriendApplication(2);
//        for(User user:users) {
//            System.out.println(user.getName());
//        }
//
////        userService.deleteFriend(1,2);
////
////        List<User> users=userService.acceptDeleteFriendMessage(2);
////
////        for(User user:users) {
////            System.out.println(user.getName());
////        }
//
////        users=userService.displayAllFriend(1);
////        for(User user:users) {
////            System.out.println(user.getName());
////        }
//    }
//
//    @Test
//    public void chat() {
////        userService.sendMessage(2,1,"你是大傻子");
//        List<User> users=userService.isUnreadMessage(1);
//        for(User user:users) {
//            System.out.println(user.getName());
//        }
//
//        List<ChatRecord> records=userService.displayAllMessage(1,2);
//        for(ChatRecord record:records) {
//            System.out.println(record.getContent());
//        }
//
//        users=userService.isUnreadMessage(1);
//        for(User user:users) {
//            System.out.println(user.getName());
//        }
//    }
//
//    @Test
//    public void sendEmail() throws Exception {
//        EmailUntil.sendEmail("1252983925@qq.com","123");
//    }
//
//    @Test
//    public void register() {
//        System.out.println(userService.sendCaptcha("1320243731@qq.com"));
//    }
}
