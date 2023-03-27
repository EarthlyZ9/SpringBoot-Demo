package com.springboot.demo.springbootdemoapp.controllers;


import com.springboot.demo.springbootdemoapp.entity.Student;
import com.springboot.demo.springbootdemoapp.exceptions.NotFoundException;
import com.springboot.demo.springbootdemoapp.exceptions.ValidationError;
import com.springboot.demo.springbootdemoapp.services.StudentService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Optional<Student> getStudent(@PathVariable int studentId) {
        try {
            return studentService.findById(studentId);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
           throw e;
        }

    }

    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId(0);
        Student newStudent;

        try {
            newStudent = studentService.createStudent(student);
        } catch (ValidationError e) {
            throw e;
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{studentId}")
            .buildAndExpand(newStudent.getId()).toUri();

        return ResponseEntity.created(location).body(newStudent);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @RequestBody Student newStudent) throws NotFoundException, ValidationError{
        try {
            return studentService.updateStudent(studentId, newStudent);
        } catch (NotFoundException | ValidationError e) {
            throw e;
        }
    }

    @PatchMapping("/{studentId}")
    public Student partialUpdateStudent(@PathVariable int studentId) {
        return null;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) throws NotFoundException {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
