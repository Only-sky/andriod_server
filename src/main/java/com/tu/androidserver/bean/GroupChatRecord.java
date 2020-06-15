package com.tu.androidserver.bean;

import java.sql.Timestamp;

public class GroupChatRecord {

    private Integer groupId;
    private Integer userId;
    private Timestamp time;
    private String content;

    public GroupChatRecord(Integer groupId, Integer userId, Timestamp time, String content) {
        this.groupId = groupId;
        this.userId = userId;
        this.time = time;
        this.content = content;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
