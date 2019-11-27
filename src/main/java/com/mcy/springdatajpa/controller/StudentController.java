package com.mcy.springdatajpa.controller;

import com.mcy.springdatajpa.entity.Student;
import com.mcy.springdatajpa.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    //注入StudentService
    @Resource
    private StudentService studentService;

    @RequestMapping("/save")
    public String save(){
        Student student = new Student();
        student.setAddress("湖北");
        student.setName("张三");
        student.setAge(18);
        student.setSex('男');

        Student student2 = new Student();
        student2.setAddress("湖北");
        student2.setName("李四");
        student2.setAge(19);
        student2.setSex('男');

        Student student3 = new Student();
        student3.setAddress("湖北");
        student3.setName("王五");
        student3.setAge(20);
        student3.setSex('男');

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);

        //保存
        studentService.saveAll(students);
        return "保存学生对象成功";
    }

    @RequestMapping("/name")
    public Student getByName(String name){
        return studentService.getStuByName(name);
    }

    @RequestMapping("/nameAndAddress")
    public List<Student> getNameAndAddress(String name, String address){
        return studentService.getStusByNameAndAddr(name, address);
    }

    @RequestMapping("/nameLike")
    public List<Student> getByNameLike(String name){
        return studentService.getStusByNameLike(name);
    }
}
