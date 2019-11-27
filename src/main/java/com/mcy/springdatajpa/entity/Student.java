package com.mcy.springdatajpa.entity;

import javax.persistence.*;

//用于标记持久化类，SpringBoot项目加载后会自动根据持久化类建表
@Entity
//设置表名为tb_student
@Table(name="tb_student")
public class Student {
    /**
     * 使用@id指定主键。使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
     * 指定主键的生存策略，mysql默认为自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String name;    //姓名
    private String address;     //地址
    private Integer age;    //年龄
    private char sex;   //性别

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Student() {
    }

    public Student(String name, String address, Integer age, char sex) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
    }
}
