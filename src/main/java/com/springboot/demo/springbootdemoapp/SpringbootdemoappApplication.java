package com.springboot.demo.springbootdemoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringbootdemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoappApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
//		return runner -> {
//           createStudent(studentDAO);
//          // readStudent(studentDAO);
//          // queryForStudents(studentDAO);
////			queryStudentsByLastName(studentDAO);
////			updateStudent(studentDAO);
////			deleteStudentById(studentDAO);
////			deleteAllStudents(studentDAO);
//		};
//	}
//
//	private void readStudent(StudentDAO studentDAO) {
//		int theId = 1;
//		System.out.println("Retrieving student with id: " + theId);
//		Student student = studentDAO.findById(theId);
//
//		System.out.println("Found student: " + student);
//	}
//
//	private void createStudent(StudentDAO studentDAO) {
//		System.out.println("Creating new student object...");
//		Student tempStudent = new Student("Jisoo", "Lee", "earthlyz9.dev@gmail.com");
//
//		System.out.println("Saving the student...");
//		studentDAO.save(tempStudent);
//
//		System.out.println("Saved student. Generated id: " + tempStudent.getId());
//	}
//
//	private void queryForStudents(StudentDAO studentDAO) {
//		List<Student> students = studentDAO.findAll();
//
//		for (Student s : students) {
//			System.out.println(s);
//		}
//	}
//
//	private void queryStudentsByLastName(StudentDAO studentDAO) {
//		List<Student> students = studentDAO.findByLastName("Lee");
//
//		for (Student s : students) {
//			System.out.println(s);
//		}
//	}
//
//	private void updateStudent(StudentDAO studentDAO) {
//		int studentId = 1;
//		Student student = studentDAO.findById(studentId);
//
//		student.setLastName("Kim");
//		System.out.println("Updating student id with: " + studentId);
//		studentDAO.update(student);
//
//		System.out.println("Updated student: " + student);
//	}
//
//	private void deleteStudentById(StudentDAO studentDAO) {
//		int studentId = 1;
//		System.out.println("Deleting student id of: " + studentId);
//		studentDAO.delete(studentId);
//		System.out.println("Deleted student id of: " + studentId);
//	}
//
//	private void deleteAllStudents(StudentDAO studentDAO) {
//		System.out.println("Deleting all students...");
//		int numRowsDeleted = studentDAO.deleteAll();
//		System.out.println("Deleted " + numRowsDeleted + " students");
//	}
}
