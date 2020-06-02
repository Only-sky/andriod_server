package com.tu.androidserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author 涂哲维
 */
@SpringBootApplication
public class AndroidServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AndroidServerApplication.class, args);
    }

}
