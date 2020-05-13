package edu.mum.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.mum.domain.UserProfile;
import edu.mum.repository.UserRepository;
@Service
@Transactional
public class UserProfileService  {
 
	@Autowired 
	UserRepository userRepository;
	 
	public UserProfile saveUser(UserProfile user)
	{    
		return userRepository.save(user);
	}
	public UserProfile getUserById(Long id)
	{
		return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}
	public List<UserProfile> getAllUser()
	{
		return (List<UserProfile>) userRepository.findAll();
	}
	
	
	 public UserProfile LoggedInUser(){

		    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   String userName=auth.getName();
		   UserProfile loggedUser=userRepository.getUserByUserName(userName).map(UserProfile::new).get();
		   return loggedUser;
		}
}
