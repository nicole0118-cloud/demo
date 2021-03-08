package com.example.demo;


import com.example.demo.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

    private MockMvc mockMvc;

    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setupMockMvc() {
        //使用MockMvc前需要进行初始化
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        User user = new User();
        user.setUsername("cary");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        session.setAttribute("user", user);
    }

    @Test
    @Transactional
    public void getTest() throws Exception {
        String ret = mockMvc.perform(MockMvcRequestBuilders.get("/user/{username}", "nicole")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("nicole"))
                //.andDo(MockMvcResultHandlers.print());
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info("++++++++++ret-->{}", ret);

        //反序列化
        List<User> userInfo = this.mapper.readValue(ret, new TypeReference<List<User>>() {
        });

        userInfo.forEach(v -> Assert.assertEquals(v.getUsername(), "nicole"));
    }


    @Test
    @Transactional
    public void saveTest() throws Exception {
        User user = new User();
        user.setUsername("nicole");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");

        String userJson = mapper.writeValueAsString(user);
        log.info("++++++++++userJson-->{}", userJson);

        String ret = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson.getBytes(StandardCharsets.UTF_8)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .toString();

        log.info("++++++++++ret-->{}", ret);
    }

}


