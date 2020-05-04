package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentDao {
    Optional<Student> getStudentById(String id);
    List<Student> getAllStudents();
    Student add(Student student);
    Student update(Student student, String id);
    void delete(String id);
}
