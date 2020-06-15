package com.tu.androidserver.service;

import com.alibaba.fastjson.JSON;
import com.tu.androidserver.bean.Group;
import com.tu.androidserver.bean.GroupChatRecord;
import com.tu.androidserver.mapper.GroupChatRecordMapper;
import com.tu.androidserver.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GroupChatRecordMapper groupChatRecordMapper;

    /**
     * 创建群聊
     * */
    public boolean createGroup(String name,Integer userId) {
        List<Integer> users=new ArrayList<>();
        users.add(userId);
        Group group=new Group(name,new Timestamp(System.currentTimeMillis()),JSON.toJSONString(users));
        if(groupMapper.insertGroup(group)==1) {
            return true;
        }
        return false;
    }

    /**
     * 加入群聊
     * @return
     * 0:没有这个群组
     * 1:已经在群租中
     * 2:添加成功
     * 3:添加失败
     * */
    public int joinGroup(Integer userId,Integer groupId) {
        Group group=groupMapper.getGroupById(groupId);
        if(group==null) {
            return 0;
        }
        List<Integer> users= (List<Integer>) JSON.parse(group.getUsers());
        if(users.contains(userId)) {
            return 1;
        }
        users.add(userId);
        group.setUsers(JSON.toJSONString(users));
        if(groupMapper.updateGroup(group)==1) {
            return 2;
        }
        return 3;
    }

    /**
     * 在群里发言
     * */
    public boolean sendGroupMessage(Integer groupId,Integer userId,String content) {
        GroupChatRecord record=new GroupChatRecord(groupId,userId,new Timestamp(System.currentTimeMillis()),content);
        if(groupChatRecordMapper.insertGroupChatRecord(record)==1) {
            return true;
        }
        return false;
    }

    /**
     * 获取群里的所有发言
     * */
    public List<GroupChatRecord> displayGroupAllMessage(Integer groupId) {
        return groupChatRecordMapper.getGroupChatRecordByGroupId(groupId);
    }
}
