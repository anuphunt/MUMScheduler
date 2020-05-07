package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    //public Course findCourseByName(String courseName);
}
