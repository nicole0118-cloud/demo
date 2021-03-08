package com.example.demo.services;


import com.example.demo.domain.User;

import java.util.List;

/**
 * @author Nicole
 */
public interface UserService extends IService<User> {
    List<User> findByName(String userName);

    void saveUser(User user);
}