package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class MyTest {
    @Test
   public void queryUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUsers(1);
        System.out.println(user);

//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("id", 2);
//        map.put("name", "天才");
//        map.put("pwd", "passwordcorrect");
//        mapper.updateUser(map);

        sqlSession.clearCache();//手动清理缓存
        System.out.println("=========================");

        User user1 = mapper.queryUsers(1);
        System.out.println(user1);
        System.out.println(user==user1);
        sqlSession.close();
    }
    @Test
    public void queryUsersById(){
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.queryUsers(1);
        System.out.println(user1);
        sqlSession1.close();

        System.out.println("===========================");

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUsers(1);
        System.out.println(user2);

        System.out.println(user1 == user2);
        sqlSession2.close();

    }
}
