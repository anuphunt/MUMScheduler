package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.ICourseDao;
import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.ICourseService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {


    @Autowired
    private ICourseDao dao;


    @Override
    public Optional<Course> getCourseById(String courseId) {
        return dao.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return dao.getAllCourses();
    }

    @Override
    public void delete(String courseId) {
        dao.delete(courseId);
    }

    @Override
    public Course add(Course course) {
        return dao.add(course);
    }

    @Override
    public Course update(Course course, String courseId) {
        return dao.update(course, courseId);
    }


}


