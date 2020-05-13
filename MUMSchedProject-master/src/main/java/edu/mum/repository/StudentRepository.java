package edu.mum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Student;
import edu.mum.domain.UserProfile;

@Repository
public interface StudentRepository  extends  CrudRepository<Student, Long>  {
	
	@Query("select s from Student s where s.id= :id")
	public Student findStudentById(@Param("id") Long studentId);
	
	
	@Query("select s from Student s where s.userprofile.email= :email")
	public Student findStudentByEmail(@Param("email") String studentEmail);
	
	public Student findByUserprofile(UserProfile userProfile);
}
