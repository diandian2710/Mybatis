package com.kuang.dao;

import com.kuang.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    //获取老师
    List<Teacher> getTeacher();
    //获取指定老师下的所有学生，已及老师的信息
    Teacher getTeacher2(@Param("tid") int id);
    Teacher getTeacher3(@Param("tid") int id);

}
