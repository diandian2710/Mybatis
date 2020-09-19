package com.kuang.dao;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user where id = #{uid}")
    User getuserById(@Param("uid") int id);
}
