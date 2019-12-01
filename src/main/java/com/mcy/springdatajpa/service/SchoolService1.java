package com.mcy.springdatajpa.service;

import com.mcy.springdatajpa.entity.Clazz;
import com.mcy.springdatajpa.entity.Stu;
import com.mcy.springdatajpa.repository.ClazzRepository1;
import com.mcy.springdatajpa.repository.StuRepository1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService1 {
    //注入数据访问层接口对象
    @Resource
    private StuRepository1 stuRepository1;
    @Resource
    private ClazzRepository1 clazzRepository1;

    @Transactional
    public void saveClazzAll(List<Clazz> clazzes){
        clazzRepository1.saveAll(clazzes);
    }

    @Transactional
    public void saveStuAll(List<Stu> stu){
        stuRepository1.saveAll(stu);
    }

    /**
     * 根据性别查询学生信息
     * @param sex
     * @return
     */
    @SuppressWarnings("serial")
    public List<Map<String, Object>> getStusBySex(char sex){
        List<Stu> stus = stuRepository1.findAll(new Specification<Stu>(){
            @Override
            public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //root.get("sex")表示获取sex这个字段名称，equal表示执行equal查询
                //相当于select s from Stu s where s.sex = ?1
                Predicate p1 = cb.equal(root.get("sex"), sex);
                return p1;
            }
        });
        List<Map<String, Object>> results = new ArrayList<>();
        //遍历查询出的学生对象，提取姓名，年龄，性别信息
        for(Stu s: stus){
            Map<String, Object> stu = new HashMap<>();
            stu.put("name", s.getName());
            stu.put("age", s.getAge());
            stu.put("sex", s.getSex());
            results.add(stu);
        }
        return results;
    }

    /**
     * 动态查询学生信息：可以根据学生对象的姓名（模糊匹配），地址查询（模糊匹配），性别，班级查询学生信息
     * 如果没有传入参数，默认查询所有的学生信息
     * @param stu
     * @return
     */
    @SuppressWarnings("serial")
    public List<Map<String, Object>> getStusByDynamic(Stu stu){
        List<Stu> stus = stuRepository1.findAll(new Specification<Stu>() {
            @Override
            public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //本集合用于封装查询条件
                List<Predicate> predicates = new ArrayList<>();
                if(stu != null){
                    //是否传入用于查询的姓名
                    if(!StringUtils.isEmpty(stu.getName())){
                        predicates.add(cb.like(root.get("name"), "%"+stu.getName()+"%"));
                    }
                    //判断是否传入查询的地址
                    if(!StringUtils.isEmpty(stu.getAddress())){
                        predicates.add(cb.like(root.get("address"), "%"+stu.getAddress()+"%"));
                    }
                    //判断是否传入查询的性别
                    if(stu.getSex() != '\0'){
                        predicates.add(cb.equal(root.get("sex"), stu.getSex()));
                    }
                    //判断是否传入用于查询的班级信息
                    if(stu.getClazz() != null && !StringUtils.isEmpty(stu.getClazz().getName())){
                        root.join("clazz", JoinType.INNER);
                        Path<String> clazzName = root.get("clazz").get("name");
                        predicates.add(cb.equal(clazzName, stu.getClazz().getName()));
                    }
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
        List<Map<String, Object>> results = new ArrayList<>();
        //遍历查询出的学生对象，提取姓名，年龄，性别信息
        for(Stu s : stus){
            Map<String, Object> stuMap = new HashMap<>();
            stuMap.put("name", s.getName());
            stuMap.put("age", s.getAge());
            stuMap.put("sex", s.getSex());
            stuMap.put("address", s.getAddress());
            stuMap.put("clazzName", s.getClazz().getName());
            results.add(stuMap);
        }
        return results;
    }

    /***
     * 分页查询某个班级的学生信息
     * @param clazzName 代表班级名称
     * @param pageIndex 代表当前查询第几页
     * @param pageSize  代表每页查询的最大数据量
     * @return
     */
    @SuppressWarnings("serial")
    public Page<Stu> getStusByPage(String clazzName, int pageIndex, int pageSize){
        //指定排序参数对象：根据id，进行降序查询
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //Specification动态查询
        Specification<Stu> spec = buildSpec(clazzName, pageIndex, pageSize);
        //分页查询学生信息，返回分页实体对象数据
        //pages对象中包含了查询出来的数据信息以及与分页相关的信息
        Page<Stu> pages = stuRepository1.findAll(spec, PageRequest.of(pageIndex-1, pageSize, sort));
        return pages;
    }

    private Specification<Stu> buildSpec(String clazzName, int pageIndex, int pageSize) {
        Specification<Stu> spec = new Specification<Stu>() {
            @Override
            public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                root.join("clazz", JoinType.INNER);
                Path<String> cn = root.get("clazz").get("name");
                Predicate p1 = cb.equal(cn, clazzName);
                return p1;
            }
        };
        return spec;
    }
}
