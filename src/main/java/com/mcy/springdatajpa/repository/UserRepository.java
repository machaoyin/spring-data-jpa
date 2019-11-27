package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
