package com.springboot.demo.springbootdemoapp.repositories;

import com.springboot.demo.springbootdemoapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
