package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    /**
     * 通过学生性别来查询学生对象
     * 此方法相当于JPQL语句代码：select s from student s where s.name=?1
     * @param name
     * @return
     */
    Student findByName(String name);

    /**
     * 通过名字和地址查询学生信息
     * 此方法相当于JPQL语句代码：select s from student s where s.name = ?1 and s.address=?2
     * @param name
     * @param address
     * @return 包含Student对象的List集合
     */
    List<Student> findByNameAndAddress(String name, String address);

    /**
     * 通过学生姓名模糊查询学生信息
     * 此方法相当于JPQL语句代码：select s from student s where s.name list ?1
     * @param name
     * @return 包含Student对象的List集合
     */
    List<Student> findByNameLike(String name);

}
