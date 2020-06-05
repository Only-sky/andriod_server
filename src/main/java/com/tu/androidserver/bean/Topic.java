package com.tu.androidserver.bean;


import java.sql.Timestamp;

public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Timestamp time;
    private Integer userId;

    public Topic(Integer id, String title, String content, Timestamp time, Integer userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public Topic(String title, String content, Timestamp time, Integer userId) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
