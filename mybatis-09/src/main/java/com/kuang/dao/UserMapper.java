package com.kuang.dao;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //根据id查询用户

   User queryUsers(@Param("id") int id);

   int updateUser(Map map);
}
