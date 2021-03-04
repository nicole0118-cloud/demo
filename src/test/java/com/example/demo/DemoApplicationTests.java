package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.services.CacheStudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CacheStudentServiceImpl studentService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        System.out.println("+++++++++++++++++test1()");
        /*
         * 配置了queryStudentBySno函数的返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问
         * 第一次查询时，缓存中不存在信息，从数据库获取
         */
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为:" + student1.getName());

        /* 第二次查询时，缓存中已存在数据信息*/
        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为:" + student2.getName());

        Student student3 = this.studentService.queryStudentBySno("002");
        System.out.println("学号" + student3.getSno() + "的学生姓名为:" + student3.getName());

        /* 第二次查询时，缓存中已存在数据信息*/
        Student student4 = this.studentService.queryStudentBySno("002");
        System.out.println("学号" + student4.getSno() + "的学生姓名为:" + student4.getName());
    }

    @Test
    public void test2() {
        System.out.println("+++++++++++++++++test2()");

        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为:" + student1.getName());

        student1.setName("cary");
        this.studentService.update(student1);

        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为:" + student2.getName());
    }
}
