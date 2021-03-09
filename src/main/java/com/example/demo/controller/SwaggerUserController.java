package com.example.demo.controller;

import com.example.demo.domain.SwaggerRestObj;
import com.example.demo.domain.SwaggerUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicole
 */
@Api(value = "用户Controller")
@Controller
@RequestMapping("swaggerUser")
public class SwaggerUserController {

    /**
     * 不需要生成API的方法或者类，只需要在上面添加@ApiIgnore注解即可
     */
    @ApiIgnore
    @GetMapping("hello/nicole")
    public @ResponseBody
    String hello() {
        return "hello,nicole";
    }


    /**
     * /@ApiOperation：描述一个类的一个方法，或者说一个接口
     * ApiImplicitParam：一个请求参数*
     */
    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    @GetMapping("/{id}")
    public @ResponseBody
    SwaggerUser getUserById(@PathVariable(value = "id") String id) {

        SwaggerUser swaggerUser = new SwaggerUser();
        swaggerUser.setId(id);
        swaggerUser.setName("nicole");
        swaggerUser.setAge(25);
        return swaggerUser;
    }


    /**
     * /@ApiOperation：描述一个类的一个方法，或者说一个接口
     */
    @ApiOperation(value = "获取用户列表", notes = "根据用户列表")
    @GetMapping("/list")
    public @ResponseBody
    List<SwaggerUser> getUserList() {
        List<SwaggerUser> list = new ArrayList<>();
        SwaggerUser user1 = new SwaggerUser();
        user1.setId("1");
        user1.setName("mrbird");
        user1.setAge(25);
        list.add(user1);

        SwaggerUser user2 = new SwaggerUser();
        user2.setId("2");
        user2.setName("cary");
        user2.setAge(29);
        list.add(user2);

        return list;
    }


    /**
     * /@ApiOperation：描述一个类的一个方法，或者说一个接口
     * ApiImplicitParam：一个请求参数*
     */
    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "com.example.demo.domain.SwaggerUser")
    @PostMapping("/add")
    public @ResponseBody
    SwaggerRestObj addUser(@RequestBody SwaggerUser user) {
        SwaggerRestObj obj = new SwaggerRestObj();
        obj.setResult("result");
        obj.setUser(user);
        return obj;
    }

    /**
     * /@ApiOperation：描述一个类的一个方法，或者说一个接口
     * ApiImplicitParam：一个请求参数*
     */
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/{id}")
    public @ResponseBody
    SwaggerRestObj deleteUser(@PathVariable(value = "id") String id) {
        SwaggerRestObj obj = new SwaggerRestObj();
        obj.setResult("result");
        obj.setUser(id);
        return obj;
    }


    /**
     * /@ApiOperation：描述一个类的一个方法，或者说一个接口
     * ApiImplicitParams：多个请求参数*
     */
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "com.example.demo.domain.SwaggerUser")})
    @PutMapping("/{id}")
    public @ResponseBody
    SwaggerRestObj updateUser(@PathVariable(value = "id") String id, @RequestBody SwaggerUser user) {
        SwaggerRestObj obj = new SwaggerRestObj();
        obj.setResult("result");
        obj.setUser("success");
        obj.setResult("result");
        obj.setUser(id);
        obj.setResult("user");
        obj.setUser(user);
        return obj;
    }
}
