package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class StudentDaoImpl implements IStudentDao {

    @Autowired
    private StudentRepository repository;

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student add(Student student) {
        return repository.save(student);
    }


    @Override
    public Student update(Student student, String id) {
       if(id!=null)
           return repository.save(student);
       else return null;
    }
}
