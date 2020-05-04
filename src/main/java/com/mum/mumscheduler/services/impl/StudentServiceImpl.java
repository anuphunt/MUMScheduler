package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {


    @Autowired
    private IStudentDao dao;

    @Override
    public Optional<Student> getStudentById(String id) {
        return dao.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public Student add(Student student) {
        return dao.add(student);
    }

    @Override
    public Student update(Student student, String id) {
        return dao.update(student, id);
    }
}
