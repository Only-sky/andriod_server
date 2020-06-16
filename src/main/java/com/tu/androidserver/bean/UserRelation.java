package com.tu.androidserver.bean;

import java.sql.Timestamp;

public class UserRelation {

    private Integer senderId;
    private Integer receiverId;
    private Timestamp timestamp;
    private Integer isAgree;          //为0时表示未同意，为1时表示同意
    private Integer isDelete;         //为0时表示存在好友关系，为1时表示是senderId删除好友，为2时表示receiverId删除好友

    public UserRelation(Integer senderId, Integer receiverId, Timestamp timestamp, Integer isAgree, Integer isDelete) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.isAgree = isAgree;
        this.isDelete = isDelete;
    }

    public UserRelation(Integer senderId, Integer receiverId, Timestamp timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.isAgree = 0;
        this.isDelete = 0;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer isAgree() {
        return isAgree;
    }

    public void setAgree(Integer agree) {
        isAgree = agree;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "UserRelation{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", isAgree=" + isAgree +
                ", isDelete=" + isDelete +
                '}';
    }
}
