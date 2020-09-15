package com.kuang.dao;

import com.kuang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserLike(String value);
    //获取全部用户
    List<User> getUserList();
    //根据id查询用户
    User getUserId(int id);

    User getUserId2(Map<String, Object> map);
    //inset一个用户
    int addUser(User user);

    int addUser2(Map<String,Object> map);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(int id);
}
