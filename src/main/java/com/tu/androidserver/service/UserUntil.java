package com.tu.androidserver.service;


import com.tu.androidserver.bean.UserRelation;
import com.tu.androidserver.mapper.UserRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUntil {
    @Autowired
    private UserRelationMapper userRelationMapper;

    /**
     * 判断两个人是否为好友关系
     * */
    public boolean isFriend(Integer senderId,Integer receiverId) {
        UserRelation relation=userRelationMapper.getUserBySenderAndReceiverId(senderId,receiverId);
        if(relation==null) {
            relation=userRelationMapper.getUserBySenderAndReceiverId(receiverId,senderId);
            if(relation==null) {
                return false;
            }
        }
        if(relation.isAgree()==0) {
            return false;
        }
        if(relation.getIsDelete()==1||relation.getIsDelete()==2) {
            return false;
        }
        return true;
    }

}
