package com.springboot.demo.springbootdemoapp.dao;

import com.springboot.demo.springbootdemoapp.entity.Student;
import java.util.List;

public interface StudentDAO {

    Student save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    Student updateById(Integer studentId, Student student);

    void deleteById(Integer studentID);

    int deleteAll();
}
