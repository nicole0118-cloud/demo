package com.example.demo.config;

import com.example.demo.domain.UserInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Nicole
 */
public class UserSerializer extends JsonSerializer<UserInfo> {

    @Override
    public void serialize(UserInfo userInfo, JsonGenerator generator, SerializerProvider provider)
            throws IOException {

        //仅序列化userName属性，且输出的key是user-name
        generator.writeStartObject();
        generator.writeStringField("user-name", userInfo.getUserName());
        generator.writeEndObject();
    }
}
