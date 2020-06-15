package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.Group;
import com.tu.androidserver.bean.GroupChatRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface GroupChatRecordMapper {
    @Insert("insert into tbl_group_chat_record(group_id,user_id,time,content) " +
            "values(#{groupId},#{userId},#{time},#{content})")
    public int insertGroupChatRecord(GroupChatRecord record);

    @Select("select * from tbl_group_chat_record where group_id=#{groupId}")
    public List<GroupChatRecord> getGroupChatRecordByGroupId(int groupId);

    @Delete("delete tbl_group_chat_record " +
            "where group_id=#{groupId} and user_id={userId} and time=#{time}")
    public int deleteAllGroupChatRecord(GroupChatRecord record);
}
