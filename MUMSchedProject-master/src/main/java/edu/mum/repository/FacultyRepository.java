package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mum.domain.Faculty;
import edu.mum.domain.UserProfile;

public interface FacultyRepository extends CrudRepository<Faculty, Long>{
	public Faculty findByUserProfileFirstName(String firstName);
	
	public Faculty findByUserProfile(UserProfile userProfile);
}