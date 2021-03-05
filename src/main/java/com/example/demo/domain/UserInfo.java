package com.example.demo.domain;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;


/**
 * JsonIgnoreProperties，忽略一组属性，作用于类上，比如JsonIgnoreProperties({ "password", "age" })。
 * <p>
 * JsonSerialize(using = UserSerializer.class)
 * 使用JsonSerialize来指定UserInfo对象的序列化方式。
 * <p>
 * JsonView，作用在类或者属性上，用来定义一个序列化组。
 * 比如对于UserIndo对象，某些情况下只返回userName属性就行，而某些情况下需要返回全部属性
 *
 * //@author Nicole
 * //@JsonIgnoreProperties({"age"})
 * //@JsonSerialize(using = UserSerializer.class)
 */
@JsonView
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView {
    }

    ;

    public interface AllUserFieldVide extends UserNameView {
    }

    ;

    @JsonView(UserNameView.class)
    private String userName;

    @JsonView(AllUserFieldVide.class)
    private int age;

    /**
     * JsonIgnore，作用在属性上，用来忽略此属性
     * password属性显示时被忽略
     * //@JsonIgnore
     */
    @JsonView(AllUserFieldVide.class)
    private String password;

    /**
     * JsonProperty，作用在属性上，用来为JSON key指定一个别名
     * birthday被替换为bth
     * JsonFormat，用于日期格式化
     */
    @JsonProperty("bth")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(AllUserFieldVide.class)
    private Date birthday;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
