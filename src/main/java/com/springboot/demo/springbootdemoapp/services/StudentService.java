package com.springboot.demo.springbootdemoapp.services;

import com.springboot.demo.springbootdemoapp.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Integer studentId);

    Student createStudent(Student newStudent);

    Student partialUpdateStudent(Integer studentId, Student newStudent);

    Student updateStudent(Integer studentId, Student newStudent);

    void deleteStudent(Integer studentId);

    void deleteAll();

}
