package com.tu.androidserver.mapper;

import com.tu.androidserver.bean.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Jordan_tu
 * @date 2020/6/2
 */
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where id=#{id}")
    public User getUserById(Integer id);

    @Select("select * from tb_user where email=#{email}")
    public User getUserByEmail(String email);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into tb_user(email,password) values(#{email},#{password}) ")
    public int insertUser(User user);

//    @Update("update department set departmentName=#{departmentName} where id=#{id}")
//    public int updateUser(Department department);

    //    @Delete("delete from department where id=#{id}")
//    public int deleteDept(Integer id);

}
