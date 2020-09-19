package com.kuang.dao;

import com.kuang.pojo.Teacher;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void getTeacher(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teacherList = mapper.getTeacher();
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
        sqlSession.close();
    }
    @Test
    public void getTeacher2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher2(1);
        System.out.println(teacher);
//        Teacher{id=1, name='秦老师', students=[Student{id=1, name='小明', tid=1}, Student{id=2, name='小红', tid=1},
//        Student{id=3, name='小张', tid=1},
//        Student{id=4, name='小李', tid=1},
//        Student{id=5, name='小王', tid=1}]}
        sqlSession.close();
    }
    @Test
    public void getteacher3(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher3(1);
        System.out.println(teacher);
        sqlSession.close();
    }
}
