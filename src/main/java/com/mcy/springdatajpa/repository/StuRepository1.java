package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.Stu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StuRepository1 extends JpaRepository<Stu, Integer>, JpaSpecificationExecutor<Stu> {

}
