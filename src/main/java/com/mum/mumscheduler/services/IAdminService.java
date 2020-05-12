package com.mum.mumscheduler.services;

import com.mum.mumscheduler.models.Admin;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    Optional<Admin> getAdminById(String id);
    List<Admin> getAllAdmins();
    void delete(String id);
    Admin add(Admin admin);
    Admin update(Admin Admin, String id);
}


