package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.Group;
import com.tu.androidserver.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into tbl_group(group_id,group_name,time,group_users) values(#{id},#{name},#{time},#{users})")
    public int insertGroup(Group group);

    @Select("select * from tbl_group where group_id=#{id}")
    public Group getGroupById(int id);

    @Update("update tbl_group set group_name=#{name},group_users=#{users} where group_id=#{id}")
    public int updateGroup(Group group);

    @Delete("delete from tbl_group where group_id=#{id}")
    public int deleteGroup(int id);
}
