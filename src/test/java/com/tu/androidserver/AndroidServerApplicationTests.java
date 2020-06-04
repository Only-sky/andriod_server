package com.tu.androidserver;

import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import com.tu.androidserver.bean.User;
import com.tu.androidserver.mapper.CommentMapper;
import com.tu.androidserver.mapper.TopicMapper;
import com.tu.androidserver.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

@SpringBootTest
class AndroidServerApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    CommentMapper commentMapper;

    @Test
    void user() throws SQLException {
//        User user = new User("hty","2784216955@qq.com","123456");
//        System.out.println(userMapper.insertUser(user));
        System.out.println(userMapper.getUserById(1).getEmail());
    }

    @Test
    public void topic() {
//        Topic topic = new Topic("测试标题1","测试内容2",new Date(System.currentTimeMillis()),1);
//        System.out.println(topicMapper.insertTopic(topic));


        for(Topic t:topicMapper.getTopicByUserId(1)) {
            System.out.println(t.getTime().toString());
        }
    }


    @Test
    public void comment() {
//        Comment comment=new Comment("测试评论1",1,2,new Timestamp(System.currentTimeMillis()));
//        commentMapper.insertTopic(comment);

        for(Comment comment:commentMapper.getCommentByUserId(1)) {
            System.out.println(comment.getContent());
        }

    }

}
