package com.tu.androidserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author 涂哲维
 */
@SpringBootApplication
@MapperScan("com.tu.androidserver.mapper")
public class AndroidServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AndroidServerApplication.class, args);
    }

}
