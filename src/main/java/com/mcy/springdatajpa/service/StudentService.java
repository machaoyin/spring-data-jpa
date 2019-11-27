package com.mcy.springdatajpa.service;

import com.mcy.springdatajpa.entity.Student;
import com.mcy.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    //注入数据访问层接口对象
    @Resource
    private StudentRepository studentRepository;

    @Transactional
    public void saveAll(List<Student> students){
        studentRepository.saveAll(students);
    }

    public Student getStuByName(String name){
        return studentRepository.findByName(name);
    }

    public List<Student> getStusByNameAndAddr(String name, String address){
        return studentRepository.findByNameAndAddress(name, address);
    }

    public List<Student> getStusByNameLike(String name){
        return studentRepository.findByNameLike("%"+name+"%");
    }
}
