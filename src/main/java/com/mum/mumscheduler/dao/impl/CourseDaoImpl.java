package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.ICourseDao;
import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.respository.CourseRepository;
import com.mum.mumscheduler.respository.StudentRepository;
import com.mum.mumscheduler.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class CourseDaoImpl implements ICourseDao {

    @Autowired
    private CourseRepository repository;

    @Override
    public void delete(String courseId) {
        repository.deleteById(courseId);
    }

    @Override
    public Optional<Course> getCourseById(String courseId) {
        return repository.findById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public Course add(Course course) {
        return repository.save(course);
    }

    @Override
    public Course update(Course course, String courseId) {
      if(courseId!=null)
            return repository.save(course);

        return null;
    }
}
