package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author Jordan_tu
 * @date 2020/6/2
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from tbl_user where user_id=#{id}")
    public User getUserById(Integer id);

    @Select("select * from tbl_user where user_email=#{email}")
    public User getUserByEmail(String email);

    @Options(useGeneratedKeys = true)
    @Insert("insert into tbl_user(user_name,user_email,user_password,user_sex,user_phone,user_address) " +
            "values(#{name},#{email},#{password},#{sex},#{phone},#{address}) ")
    public int insertUser(User user);

    @Update("update tbl_user set user_name=#{name},user_password=#{password},user_sex=#{sex}," +
            "user_phone=#{phone},user_address=#{address} where user_id=#{id}")
    public int updateUser(User user);

    @Delete("delete from tbl_user where user_id = #{userId}")
    public int deleteUser(Integer userId);


}
