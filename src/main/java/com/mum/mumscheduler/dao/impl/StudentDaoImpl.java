package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.models.User;
import com.mum.mumscheduler.respository.StudentRepository;
import com.mum.mumscheduler.respository.UserRepository;
import com.mum.mumscheduler.utilities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class StudentDaoImpl implements IStudentDao {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student add(Student student) {
        Student student1 = studentRepository.save(student);
        userRepository.save(new User(student1.getId(), student1.getUsername(), student1.getPassword(), UserRole.STUDENT));
        return student;
    }

    @Override
    public Student update(Student student, String id) {
       if(id!=null){
           Student student1 = studentRepository.save(student);
           userRepository.save(new User(student1.getId(), student1.getUsername(), student1.getPassword(), UserRole.STUDENT));
           return student1;
       }
       else return null;
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.getStudentByUsername(username);
    }
}
