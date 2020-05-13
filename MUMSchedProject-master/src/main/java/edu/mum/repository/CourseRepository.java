package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
   public Course findByCourseName(String courseName);
   
}
