package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    SqlSession sqlSession;
    @Test
    public void getUserLike(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserLike("%wang%");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void test() {

        try {
            //第一步：获得sqlSession对象
           sqlSession = MybatisUtils.getSqlSession();
            //方式一：getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            //方式二：不建议使用
//        List<User> userList = sqlSession.selectList("com.kuang.dao.UserDao.getUserList");

            for (User user : userList) {
                System.out.println(user);
            }
            }catch(Exception e) {
                e.printStackTrace();
            }finally{
                //关闭sqlsession
                sqlSession.close();
            }
    }

    @Test
    public void getUserById2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", 2);
        User user = mapper.getUserId2(map);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void getUserById(){
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserId(1);
            System.out.println(user);
            sqlSession.close();

    }
    //增删改需要提交事物
    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.addUser(new User(4,"Neil","123333"));
        if(res!=0){
            System.out.println("插入成功");
        }
        //提交事物!!!非常重要
        sqlSession.commit();
        //结束session
        sqlSession.close();
    }

    @Test
    public void add2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", 5);
//        map.put("userName", "ultraman");
//        map.put("passWord", "123456666");

        int res = mapper.addUser2(map);
        if (res!=0){
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.updateUser(new User(2,"Neil122223","123123123"));
        if (res!=0){
            System.out.println("update success");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delete(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.deleteUser(5);
        if (res!=0){
            System.out.println("delete success");
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
