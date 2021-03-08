package com.example.demo.Mapper;

import com.example.demo.config.MyMapperConfig;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Nicole
 */
@Mapper
public interface UserMapper extends MyMapperConfig<User> {
}
