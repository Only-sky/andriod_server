package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.Comment;
import com.tu.androidserver.bean.Topic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    @Options(useGeneratedKeys = true)
    @Insert("insert into tbl_comment(comment_content,user_id,topic_id,comment_time) values(#{content},#{userId},#{topicId},#{time}) ")
    public int insertComment(Comment comment);

    @Select("select * from tbl_comment where comment_id=#{id}")
    public Comment getCommentById(int id);

    @Select("select * from tbl_comment where user_id=#{id}")
    public List<Comment> getCommentByUserId(int id);

    @Select("select * from tbl_comment where topic_id=#{id}")
    public List<Comment> getCommentByTopicId(int id);

    @Delete("delete * from tbl_comment where comment_id=#{id}")
    public int deleteCommentById(int id);

    @Update("update tbl_comment set comment_content=#{content} where commend_id=#{id}")
    public int updateUser(Comment comment);
}
