package com.example.demo.config;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Nicole
 */
public interface MyMapperConfig<T> extends Mapper<T>, MySqlMapper<T>{
}
