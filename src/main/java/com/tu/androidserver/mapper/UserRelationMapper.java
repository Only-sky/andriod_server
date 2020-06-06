package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.UserRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserRelationMapper {

    @Select("select * from tbl_user_relation where sender_id=#{id}")
    public List<UserRelation> getUserBySenderId(Integer id);

    @Select("select * from tbl_user_relation where receiver_id=#{id}")
    public List<UserRelation> getUserByReceiverId(Integer id);

    @Select("select * from tbl_user_relation where sender_id=#{senderId} and receiver_id=#{receiverId}")
    public UserRelation getUserBySenderAndReceiverId(Integer senderId,Integer receiverId);

    @Insert("insert into tbl_user_relation(sender_id,receiver_id,time,is_agree,is_delete) " +
            "values(#{senderId},#{receiverId},#{timestamp},#{isAgree},#{isDelete}) ")
    public int insertUserRelation(UserRelation userRelation);

    @Update("update tbl_user_relation set is_agree=#{isAgree},is_delete=#{isDelete} " +
            "where sender_id=#{senderId} and receiver_id=#{receiverId}")
    public int updateUserRelation(UserRelation userRelation);

    @Delete("delete from tbl_user_relation where sender_id=#{senderId} and receiver_id=#{receiverId}")
    public int deleteUserRelation(Integer senderId,Integer receiverId);

}
