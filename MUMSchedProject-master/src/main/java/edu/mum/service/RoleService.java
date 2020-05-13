package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Role;
import edu.mum.repository.RoleRepository;
@Service
public class RoleService {
	
	@Autowired
	RoleRepository userTypeRepository;
	
	public Role save(Role userType)
	{
		return userTypeRepository.save(userType);
	}
	public List<Role> getAll()
	{
		return (List<Role>) (userTypeRepository.findAll());
	}
	public Role findOne(Long id)
	{
		return userTypeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}
	
	public void deleteRole(Long id){
		userTypeRepository.deleteById(id);
	}

}
