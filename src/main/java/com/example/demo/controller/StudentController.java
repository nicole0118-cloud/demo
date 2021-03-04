package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Nicole
 */
@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping( value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return this.studentService.queryStudentBySno(sno);
    }

    @RequestMapping(value = "/queryallstudent")
    public List<Map<String, Object>> queryAllStudent() {
        return this.studentService.queryStudentListMap();
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public int saveStudent(String sno,String name,String sex) {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);

        Student stu = this.studentService.queryStudentBySno(sno);

        if(stu != null){
            log.info("++++++++the student is already existed!");
            return -1;
        }
        return this.studentService.add(student);
    }

    @RequestMapping(value = "deletestudent", method = RequestMethod.GET)
    public int deleteStudentBySno(String sno) {
        return this.studentService.deleteBysno(sno);
    }
}
