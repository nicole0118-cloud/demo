package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nicole
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("user/{userName}")
    public List<User> getUserByName(@PathVariable(value = "userName") String userName) {
        return this.userService.findByName(userName);
    }

    @PostMapping("user/save")
    public void saveUser(@RequestBody User user) {
        this.userService.saveUser(user);
    }
}
