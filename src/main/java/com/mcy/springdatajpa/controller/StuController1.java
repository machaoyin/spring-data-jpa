package com.mcy.springdatajpa.controller;

import com.mcy.springdatajpa.custom.PageData;
import com.mcy.springdatajpa.entity.Clazz;
import com.mcy.springdatajpa.entity.Stu;
import com.mcy.springdatajpa.service.SchoolService1;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stu1")
public class StuController1 {
    //注入SchoolService1
    @Resource
    private SchoolService1 schoolService1;

    //保存，初始化数据
    @RequestMapping("/save")
    public String save(){
        Clazz clazz1 = new Clazz("软件工程1班");
        Clazz clazz2 = new Clazz("软件工程2班");
        //保存班级对象数据
        List<Clazz> clazzs = new ArrayList<>();
        clazzs.add(clazz1);
        clazzs.add(clazz2);
        schoolService1.saveClazzAll(clazzs);

        Stu stu1 = new Stu("张三", "湖北", 20, '男', clazz1 );
        Stu stu2 = new Stu("李四", "湖北", 18, '女', clazz1 );
        Stu stu3 = new Stu("诸葛亮", "湖北", 19, '女', clazz1 );
        Stu stu4 = new Stu("刘备", "湖北", 21, '男', clazz2 );
        Stu stu5 = new Stu("张飞", "湖北", 32, '女', clazz2 );
        Stu stu6 = new Stu("关于", "湖北", 17, '男', clazz2 );

        List<Stu> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);
        stus.add(stu4);
        stus.add(stu5);
        stus.add(stu6);
        schoolService1.saveStuAll(stus);
        return "保存学生对象成功";
    }

    @RequestMapping("/getStusBySex")
    public List<Map<String, Object>> getStusBySex(char sex){
        return schoolService1.getStusBySex(sex);
    }

    //动态查询学生信息
    //可以根据学生对象的姓名（模糊匹配），地址查询（模糊匹配），性别，班级查询学生信息
    @RequestMapping("/getStusByDynamic")
    List<Map<String, Object>> getStusByDynamic(Stu stu){
        return schoolService1.getStusByDynamic(stu);
    }

    //分页查询摸个班级的学生信息
    @RequestMapping("/getStusBypage")
    PageData getStusByPage(String clazzName, int pageIndex, int pageSize){
        //分页查询某个班级的学生信息
        Page<Stu> page = schoolService1.getStusByPage(clazzName, pageIndex, pageSize);
        //对查询出来的结果数据进行分析
        List<Stu> stus = page.getContent();
        List<Map<String, Object>> stuDatas = new ArrayList<>();
        for(Stu s: stus){
            Map<String, Object> stuMap = new HashMap<>();
            stuMap.put("name", s.getName());
            stuMap.put("id", s.getId());
            stuMap.put("age", s.getAge());
            stuMap.put("sex", s.getSex());
            stuMap.put("address", s.getAddress());
            stuMap.put("clazzName", clazzName);
            stuDatas.add(stuMap);
        }
        //将分页查询出的结果数据进行分析
        //然后把数据存入PageData对象中响应给浏览器展示
        PageData data = new PageData();
        data.setStuDates(stuDatas);
        data.setPageIndex(page.getNumber()+1);
        data.setPageSize(page.getTotalPages());
        data.setTotalCount(page.getTotalElements());
        data.setPageNum(page.getSize());
        return data;
    }
}
