package com.mcy.springdatajpa.entity;

import javax.persistence.*;

//用于标记持久化类，SpringBoot项目加载后会自动根据持久化类建表
@Entity
//设置表名为tb_user
@Table(name = "tb_user")
public class User {
    /**
     * 使用@id指定主键。使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
     * 指定主键的生存策略，mysql默认为自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String username;    //姓名，username
    private String loginName;   //登录名
    private char sex;   //性别
    private Integer age;    //年龄

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public User(String username, String loginName, char sex, Integer age) {
        this.username = username;
        this.loginName = loginName;
        this.sex = sex;
        this.age = age;
    }
}
