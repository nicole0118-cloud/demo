package com.example.demo.domain;

import java.util.List;

/**
 * Could not resolve pointer: /definitions/Map«string,object» does not exist in document
 * 因为swagger返回map类型报错，所以将map定义在对象中，面向对象编程，而且这样生成文档的时候，注释也会显示好
 *
 * @author Nicole
 */
public class SwaggerRestObj {
    String result;
    Object user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}
