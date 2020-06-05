package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.Topic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@Mapper
public interface TopicMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into tbl_topic(topic_title,topic_content,topic_time,user_id) values(#{title},#{content},#{time},#{userId}) ")
    public int insertTopic(Topic topic);

    @Select("select * from tbl_topic where topic_id=#{id}")
    public Topic getTopicById(int id);

    @Select("select * from tbl_topic")
    public List<Topic> getAllTopic();

    @Select("select * from tbl_topic where user_id=#{id}")
    public List<Topic> getTopicByUserId(int id);

    @Delete("delete * from tbl_topic where topic_id=#{id}")
    public int deleteTopicById(int id);

    @Update("update tbl_topic set topic_title=#{title},topic_content=#{content},topic_time=#{time}")
    public int updateUser(Topic topic);

}
