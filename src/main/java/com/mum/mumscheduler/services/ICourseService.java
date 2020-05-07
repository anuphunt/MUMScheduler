package com.mum.mumscheduler.services;


import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    Optional<Course> getCourseById(String courseId);
    List<Course> getAllCourses();
    void delete(String courseId);
    Course add(Course course);
    Course update(Course course, String courseId);
}
