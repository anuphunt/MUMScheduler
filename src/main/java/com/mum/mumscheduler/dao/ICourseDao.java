package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface ICourseDao {
    Optional<Course> getCourseById(String courseId);
    List<Course> getAllCourses();
    Course add(Course course);
    Course update(Course course, String courseId);
    void delete(String courseId);
}
