package com.example.demo.services;

import com.example.demo.dao.StudentDao;
import com.example.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Nicole
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao studentMapper;

    public int add(Student student) {
        return this.studentMapper.add(student);
    }


    public int update(Student student) {
        return this.studentMapper.update(student);
    }


    public int deleteBysno(String sno) {
        return this.studentMapper.deleteBysno(sno);
    }

    public Student queryStudentBySno(String sno) {
        return this.studentMapper.queryStudentBySno(sno);
    }

    public List<Map<String, Object>> queryStudentListMap() {
        return this.studentMapper.queryStudentsListMap();
    }
}
