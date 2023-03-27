package com.springboot.demo.springbootdemoapp.dao;

import com.springboot.demo.springbootdemoapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOJpaImpl implements StudentDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student save(Student theStudent) {
        // if id == 0 -> save / else -> update
        Student newStudent = entityManager.merge(theStudent);
        return newStudent;
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query -> JPQL
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by id asc", Student.class);

        // return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student where lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public Student updateById(Integer studentId, Student student) {
        Student s = entityManager.merge(student);
        return s;
    }

    @Override
    public void deleteById(Integer studentId) {
        Student student = findById(studentId);
        entityManager.remove(student);
    }

    @Override
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
