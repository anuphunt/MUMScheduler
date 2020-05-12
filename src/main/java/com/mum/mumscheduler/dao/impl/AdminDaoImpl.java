package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IAdminDao;
import com.mum.mumscheduler.models.Admin;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.models.User;
import com.mum.mumscheduler.respository.AdminRepository;
import com.mum.mumscheduler.respository.StudentRepository;
import com.mum.mumscheduler.respository.UserRepository;
import com.mum.mumscheduler.utilities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class AdminDaoImpl implements IAdminDao {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void delete(String id) {
        adminRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin add(Admin admin) {
        Admin admin1 = adminRepository.save(admin);
        userRepository.save(new User(admin1.getId(), admin1.getUsername(), admin1.getPassword(), UserRole.ADMIN));
        return admin1;
    }

    @Override
    public Admin update(Admin admin, String id) {

        admin.setId(id);

//        Admin admin1 = adminRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Admin Id: " + id));
//        admin1.setUsername(admin.getUsername());
//        admin1.setAddress(admin.getAddress());
//        admin1.setPassword(admin.getPassword());
//        admin1.setEmail(admin.getEmail());
//        admin1.setJoinedDate(admin.getJoinedDate());
//        admin1.setName(admin.getName());
        return adminRepository.save(admin);
    }
}
