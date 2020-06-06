package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.ChatRecord;
import com.tu.androidserver.bean.UserRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatRecordMapper {

    @Select("select * from tbl_chat_record where sender_id=#{id}")
    public List<ChatRecord> getChatRecordBySenderId(Integer id);

    @Select("select * from tbl_chat_record where receiver_id=#{id}")
    public List<ChatRecord> getChatRecordByReceiverId(Integer id);

    @Select("select * from tbl_chat_record where sender_id=#{senderId} and receiver_id=#{receiverId}")
    public List<ChatRecord> getChatRecordBySenderAndReceiverId(Integer senderId,Integer receiverId);

    @Select("select * from tbl_chat_record where sender_id=#{senderId} and receiver_id=#{receiverId} and is_read='0'")
    public List<ChatRecord> getUnreadChatRecordBySenderAndReceiverId(Integer senderId,Integer receiverId);

    @Insert("insert into tbl_chat_record(sender_id,receiver_id,content,time,is_read) " +
            "values(#{senderId},#{receiverId},#{content},#{time},#{isRead})")
    public int insertChatRecord(ChatRecord chatRecord);

    @Update("update tbl_chat_record set is_read=#{isRead} " +
            "where sender_id=#{senderId} and receiver_id=#{receiverId} and time=#{time}")
    public int updateChatRecordState(ChatRecord chatRecord);

    @Delete("delete from tbl_chat_record where sender_id=#{senderId} and receiver_id=#{receiverId}")
    public int deleteAllChatRecord(Integer senderId,Integer receiverId);
}
