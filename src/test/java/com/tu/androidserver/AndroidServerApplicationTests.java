package com.tu.androidserver;

import com.tu.androidserver.bean.User;
import com.tu.androidserver.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class AndroidServerApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {
        User user = userMapper.getUserByEmail("1252983925@qq.com");
        System.out.println(user.getPassword());
    }

}
