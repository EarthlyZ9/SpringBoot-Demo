package com.springboot.demo.springbootdemoapp.services;

import com.springboot.demo.springbootdemoapp.entity.Student;
import com.springboot.demo.springbootdemoapp.exceptions.NotFoundException;
import com.springboot.demo.springbootdemoapp.exceptions.ValidationError;
import com.springboot.demo.springbootdemoapp.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
//    private StudentDAO studentRepository;
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Integer studentId) throws NotFoundException {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) throw new NotFoundException("Student with the provided id doesn't exist");
        return student;
    }

    @Override
    @Transactional
    public Student createStudent(Student newStudent) throws ValidationError {
        if (!newStudent.getEmail().contains("@")) {
            throw new ValidationError("invalid email");
        }
        return studentRepository.save(newStudent);
    }

    @Override
    public Student partialUpdateStudent(Integer studentId, Student newStudent) {
        return null;
    }

    @Override
    @Transactional
    public Student updateStudent(Integer studentId, Student newStudent) throws NotFoundException, ValidationError {
        Optional<Student> oldStudent = studentRepository.findById(studentId);

        if (oldStudent.isEmpty()) throw new NotFoundException("Student with the provided id doesn't exist");

        Student s = oldStudent.get();

        if (s.getEmail() != null && !s.getEmail().contains("@")) {
            throw new ValidationError("invalid email");
        }
        newStudent.setId(studentId);

        return studentRepository.save(newStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) throw new NotFoundException("Student with the provided id doesn't exist");

        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
