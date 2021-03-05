package com.example.demo.services;

import com.example.demo.domain.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Nicole
 */
@CacheConfig(cacheNames = "student")
public interface CacheStudentService {
    /**
     * update student info
     *
     * 更新数据的时候，应该使用@CachePut(key="#p0.sno")进行缓存数据的更新
     * @param student student
     * @return student
     */
    @CachePut(key = "#p0.sno")
    Student update(Student student);

    /**
     * delete student info
     *
     * 更CacheEvict：配置于函数上，通常用在删除方法上，用来从缓存中移除相应数据
     * allEntries：非必需，默认为false。当为true时，会移除所有数据
     * @param sno sno
     */
    @CacheEvict(key = "#p0", allEntries = true)
    void deleteStudentBySno(String sno);

    /**
     * query student info
     *
     * Cacheable(key = "#p0")：使用函数第一个参数作为缓存的key值
     * @param sno sno
     * @return student
     */
    @Cacheable(key = "#p0")
    Student queryStudentBySno(String sno);
}
