package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClazzRepository1 extends JpaRepository<Clazz, Integer>, JpaSpecificationExecutor<Clazz> {

}
