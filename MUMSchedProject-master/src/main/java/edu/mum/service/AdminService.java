package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Admin;
import edu.mum.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired 
	AdminRepository adminRepository;
	
	public Admin save(Admin admin){
		return  adminRepository.save(admin);
		
	}
	
	public List<Admin> getAll(){
		
		return (List<Admin>) adminRepository.findAll();
	}
	
     public Admin getAdminById(Long id){
		
		return adminRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}
}
