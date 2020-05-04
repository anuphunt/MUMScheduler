package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    public Student findStudentByName(String name);
}
