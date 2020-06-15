package com.tu.androidserver.bean;

import java.sql.Timestamp;
import java.util.List;

public class Group {

    private Integer id;
    private String name;
    private Timestamp time;
    private String users;

    public Group(Integer id, String name, Timestamp time, String users) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.users = users;
    }

    public Group(String name, Timestamp time, String users) {
        this.name = name;
        this.time = time;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
