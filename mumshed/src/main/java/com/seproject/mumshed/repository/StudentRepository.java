package com.seproject.mumshed.repository;

import com.seproject.mumshed.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
    public Student findStudentBy(String name);
}