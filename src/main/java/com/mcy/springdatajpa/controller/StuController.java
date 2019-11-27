package com.mcy.springdatajpa.controller;

import com.mcy.springdatajpa.entity.Clazz;
import com.mcy.springdatajpa.entity.Stu;
import com.mcy.springdatajpa.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stu")
public class StuController {
    //注入ShcoolService
    @Resource
    private SchoolService schoolService;

    //保存，初始化数据
    @RequestMapping("/save")
    public String save(){
        Clazz clazz1 = new Clazz("软件工程1班");
        Clazz clazz2 = new Clazz("软件工程2班");
        //保存班级对象数据
        List<Clazz> clazzs = new ArrayList<>();
        clazzs.add(clazz1);
        clazzs.add(clazz2);
        schoolService.saveClazzAll(clazzs);

        Stu stu1 = new Stu("张三", "湖北", 20, '男', clazz1 );
        Stu stu2 = new Stu("李四", "湖北", 18, '女', clazz1 );
        Stu stu3 = new Stu("王五", "湖北", 17, '男', clazz2 );

        List<Stu> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);
        schoolService.saveStuAll(stus);
        return "数据保存成功！";
    }

    /**
     * 查询某个班级所有学生的姓名，年龄，性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/getClazzStus")
    public List<Map<String, Object>> getClazzStus(String clazzName){
        return schoolService.getStusByClazzName(clazzName);
    }

    /**
     * 查询某个班级所有学生的姓名，性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/findNameAndSexByClazzName")
    public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName){
        return schoolService.findNameAndSexByClazzName(clazzName);
    }

    /**
     * 查询某个班级某种性别的所有学生的姓名
     * @param clazzName
     * @param sex
     * @return
     */
    @RequestMapping("/findNameByClazzNameAndSex")
    public List<String> findNameByClazzNameAndSex(String clazzName, Character sex){
        return schoolService.findNameByClazzNameAndSex(clazzName, sex);
    }

    /**
     * 查询某个学生属于哪个班级
     * @param stuName
     * @return
     */
    @RequestMapping("/findClazzNameByStuName")
    public String findClazzNameByStuName(String stuName){
        return schoolService.findClazzNameByStuName(stuName);
    }

    /**
     * 删除某个学生对象
     * @param stuName
     * @return
     */
    @RequestMapping("/deleteStuByStuName")
    public String deleteStuByStuName(String stuName){
        return "删除数据："+schoolService.deleteStuByStuName(stuName);
    }
}
