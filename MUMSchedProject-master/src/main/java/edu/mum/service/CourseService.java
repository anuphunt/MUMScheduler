package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Course;
import edu.mum.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	public Course save(Course course) {
		return courseRepository.save(course);
	}

	public List<Course> getAllCourser() {
		return (List<Course>) courseRepository.findAll();
	}

	public Course getCourseByName(String courseName) {
		return courseRepository.findByCourseName(courseName);
	}

	public void deleteEntry(Long id) {
		courseRepository.deleteById(id);

	}
	
	public Course getCourseById(Long id){
		return courseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}

    public void deleteCourse(String id) {
    }
}
