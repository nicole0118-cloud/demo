package com.example.demo.services;

import com.example.demo.Mapper.CacheStudentMapper;
import com.example.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicole
 */
@Service
public class CacheStudentServiceImpl implements CacheStudentService{

    @Autowired
    private CacheStudentMapper studentMapper;

    @Override
    public Student update(Student student){
        this.studentMapper.update(student);
        return this.studentMapper.queryStudentBySno(student.getSno());
    }

    @Override
    public void deleteStudentBySno(String sno){
        this.studentMapper.deleteStudentBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno){
        return this.studentMapper.queryStudentBySno(sno);
    }

}
