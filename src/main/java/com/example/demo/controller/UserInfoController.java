package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * ResponseBody注解可以将方法返回的对象序列化成JSON
 *
 * @author Nicole
 */
@RestController
@ResponseBody
@Slf4j
public class UserInfoController {

    @Autowired
    private ObjectMapper mapper;

    @JsonView(UserInfo.AllUserFieldVide.class)
    @RequestMapping("/getuser")
    public UserInfo getUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("cary");
        userInfo.setBirthday(new Date());
        return userInfo;
    }

    /**
     * 通过mapper的writeValueAsString方法将java对象序列化为JSON格式字符串
     */
    @RequestMapping("/serialization")
    public String serialization() {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("cary");
            userInfo.setBirthday(new Date());
            String str = mapper.writeValueAsString(userInfo);
            log.info("+++++++++++userInfo-->{}", str);

            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 反序列化方法
     */
    @RequestMapping("/readjsonstring")
    public String readJsonString() {
        try {
//            String json = "{\"name\":\"cary\", \"age\":30}";
//            JsonNode node = this.mapper.readTree(json);
//
//            String name = node.get("name").asText();
//            int age = node.get("age").asInt();
//

            String json = "{\"name\":\"cary\",\"hobby\":{\"first\":\"sleep\",\"second\":\"est\"}}";
            JsonNode node = this.mapper.readTree(json);

            String name = node.get("name").asText();
            JsonNode hobbyNode = node.get("hobby");
            String first = hobbyNode.get("first").asText();
            String second = hobbyNode.get("second").asText();
            String col = "name:" + name + "  " + "hobby first:" + first + "  " + "hobby second:" + second;

            return col;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 反序列化方法-绑定java对象和json数据
     */
    @RequestMapping("/readjsonobject")
    public String readJsonObject() {
        try {
            String json = "{\"userName\":\"cary\", \"age\":30}";
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            String name = userInfo.getUserName();
            int age = userInfo.getAge();

            return name + "  " + age;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 集合反序列化
     * 为了提供泛型信息，Jackson提供了JavaType ，用来指明集合类型
     */
    @RequestMapping("/customize")
    public StringBuilder customize() throws JsonProcessingException {
        String jsonStr = "[{\"userName\":\"cary\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";

        //泛型信息
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, UserInfo.class);
        List<UserInfo> list = mapper.readValue(jsonStr, type);

        StringBuilder msg = new StringBuilder();
        for (UserInfo userInfo : list) {
            msg.append(userInfo.getUserName());
            msg.append(" ");
        }

        return msg;
    }
}

