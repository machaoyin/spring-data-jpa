package com.mcy.springdatajpa.service;

import com.mcy.springdatajpa.entity.Clazz;
import com.mcy.springdatajpa.entity.Stu;
import com.mcy.springdatajpa.repository.ClazzRepository;
import com.mcy.springdatajpa.repository.StuRepository;
import com.mcy.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {
    //注入数据访问层接口对象
    @Resource
    private StuRepository stuRepository;
    @Resource
    private ClazzRepository clazzRepository;

    //事务管理
    @Transactional
    public void saveClazzAll(List<Clazz> clazzes){
        clazzRepository.saveAll(clazzes);
    }

    @Transactional
    public void saveStuAll(List<Stu> stus){
        stuRepository.saveAll(stus);
    }

    public List<Map<String, Object>> getStusByClazzName(String clazzName){
        //使用"-",驼峰式命名和@Query查询方式结果一致
        List<Stu> stus = stuRepository.findByClazz_name(clazzName);
        //List<Stu> stus = stuRepository.findByClazzName(clazzName);
        //List<Stu> stus = stuRepository.findStuByClazzName(clazzName);

        List<Map<String, Object>> results = new ArrayList<>();
        //遍历查询出的学生对象，提取姓名，年龄，性别信息
        for(Stu stu : stus){
            Map<String, Object> s = new HashMap<>();
            s.put("name", stu.getName());
            s.put("age", stu.getAge());
            s.put("sex", stu.getSex());
            results.add(s);
        }
        return results;
    }

    public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName){
        return stuRepository.findNameAndSexByClazzName(clazzName);
    }

    public List<String> findNameByClazzNameAndSex(String clazzName, char sex){
        return stuRepository.findNameByClazzNameAndSex(clazzName, sex);
    }

    public String findClazzNameByStuName(String stuName){
        return stuRepository.findClazzNameByStuName(stuName);
    }

    @Transactional
    public Integer deleteStuByStuName(String stuName){
        return stuRepository.deleteStuByStuName(stuName);
    }
}
