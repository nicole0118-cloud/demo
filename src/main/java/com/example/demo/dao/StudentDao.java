package com.example.demo.dao;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Nicole
 */

/**
 * 使用Mybaits数据库框架*/

//@Component
//@Mapper
//public interface StudentDao {
//
//    @Insert("insert into student(sno,name,sex) values(#{sno},#{name},#{sex})")
//    int add(Student student);
//
//    @Update("update student set name=#{name},sex=#{sex} where sno=#{sno}")
//    int update(Student student);
//
//    @Delete("delete from student where sno=#{sno}")
//    int deleteBysno(String sno);
//
//    @Select("select * from student where sno=#{sno}")
//    @Results(id = "student",value= {
//            @Result(property = "sno", column = "sno", javaType = String.class),
//            @Result(property = "name", column = "name", javaType = String.class),
//            @Result(property = "sex", column = "sex", javaType = String.class)
//    })
//    Student queryStudentBySno(String sno);
//}

/**
 * 使用JdbcTemplate数据库框架*/
public interface StudentDao {
    int add(Student student);

    int update(Student student);

    int deleteBysno(String sno);

    List<Map<String,Object>> queryStudentsListMap();

    Student queryStudentBySno(String sno);
}



