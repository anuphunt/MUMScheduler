package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Student;
import edu.mum.domain.UserProfile;
import edu.mum.repository.StudentRepository;



 @Service
 public class StudentService   {
	
	    @Autowired
		StudentRepository studentDAO;
		
		
		public void save(Student student){
			studentDAO.save(student);
			return ;
		}

	
		public Student getStudentByEmail(String email) {
			return studentDAO.findStudentByEmail(email);
		}

		
		public Student getStudentById(Long studentId) {
			return studentDAO.findStudentById(studentId);
		}
		
		
		public List<Student> getAllstudents()
		{
			return (List<Student>) studentDAO.findAll();
		}
		
				
		public void deleteStudent(Long id)
		{
			studentDAO.deleteById(id);
		}
		
		public Student getStudentByUserProfile(UserProfile userProfile)
		{
			return studentDAO.findByUserprofile(userProfile);
		}
}
 
