package com.springboot.demo.springbootdemoapp.repositories;

import com.springboot.demo.springbootdemoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
