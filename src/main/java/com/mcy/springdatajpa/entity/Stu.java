package com.mcy.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

//用于标记持久化类，SpringBoot项目加载后会自动根据持久化类建表
@Entity
//设置表名为tb_stu
@Table(name="tb_stu")
public class Stu {
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
    //@JsonIgnore注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉
    @JsonIgnore
    private Clazz clazz;

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

    //关联表对应关系，学生与班级的多对一的关系
    @ManyToOne
    @CreatedBy
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Stu() {
    }

    public Stu(String name, String address, Integer age, char sex, Clazz clazz) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.clazz = clazz;
    }
}
