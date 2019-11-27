package com.mcy.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//用于标记持久化类，SpringBoot项目加载后会自动根据持久化类建表
@Entity
//设置表名为tb_clazz
@Table(name="tb_clazz")
public class Clazz {
    /**
     * 使用@id指定主键。使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
     * 指定主键的生存策略，mysql默认为自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String name;    //班级名称
    //@JsonIgnore注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉
    @JsonIgnore
    private List<Stu> stus = new ArrayList<>();

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

    //班级与学生是一对多的关联，mappedBy对应stus字段
    @OneToMany(cascade=CascadeType.ALL,mappedBy="clazz")
    public List<Stu> getStus() {
        return stus;
    }

    public void setStus(List<Stu> stus) {
        this.stus = stus;
    }

    public Clazz(String name) {
        this.name = name;
    }

    public Clazz() {
    }
}
