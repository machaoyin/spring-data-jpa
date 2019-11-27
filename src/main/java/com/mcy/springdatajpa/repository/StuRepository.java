package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.Stu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StuRepository extends JpaRepository<Stu, Integer> {
    /**
     * 根据班级名称查询这个班级所有学生的信息
     * 相当于JPQL语句：select s from stu s where s.clazzname = ?1
     * @param clazzNam
     * @return
     */
    List<Stu> findByClazz_name(String clazzNam);

    /**
     * 根据班级名称查询这个班级所有学生的信息
     * 相当于JPQL语句：select s from stu s where s.clazzname = ?1
     * @param clazzNam
     * @return
     */
    List<Stu> findByClazzName(String clazzNam);

    /**
     * @Query方法
     * 根据班级名称查询这个班级所有学生的信息
     * ?1此处使用的是参数的位置，代表的是第一个参数
     * 此写法与findByClazz_name方法实现的功能完全一致
     * @param clazzName
     * @return
     */
    @Query("select s from Stu s where s.clazz.name = ?1")
    List<Stu> findStuByClazzName(String clazzName);

    /**
     * 使用@Query注解的形式，查询某个班级下所有学生的姓名和性别
     * @param clazzName
     * @return
     */
    @Query("select new Map(s.name, s.sex) from Stu s where s.clazz.name = ?1")
    List<Map<String, Object>> findNameAndSexByClazzName(String clazzName);

    /**
     * 使用@Query注解的形式，查询某个班级下某种性别的所有学生的姓名
     * 上面方法用的是参数的位置来查询的，spring-data-jpa中还支持用名称来匹配查询，使用格式“:参数名称”引用
     * @param clazzName
     * @param sex
     * @return
     */
    @Query("select s.name from Stu s where s.clazz.name = :clazzName and s.sex = :sex")
    List<String> findNameByClazzNameAndSex(@Param("clazzNam")String clazzName, @Param("sex")char sex);

    /**
     * 使用@Query注解的形式，查询某个学生属于哪个班级
     * @param stuName
     * @return
     */
    @Query("select c.name from Clazz c inner join c.stus s where s.name = ?1")
    String findClazzNameByStuName(String stuName);

    /**
     * 执行更新查询，使用@Query与@Modifying可以执行更新操作
     * 例如根据姓名删除学生
     * @param stuName
     * @return
     */
    @Modifying
    @Query("delete from Stu s where s.name = ?1")
    Integer deleteStuByStuName(String stuName);
}
