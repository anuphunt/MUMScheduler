package com.mum.mumscheduler.services;


import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    Optional<Student> getStudentById(String id);
    List<Student> getAllStudents();
    void delete(String id);
    Student add(Student student);
    Student update(Student student, String id);
}
