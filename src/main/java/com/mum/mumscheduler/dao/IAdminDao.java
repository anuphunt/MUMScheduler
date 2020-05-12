package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Admin;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IAdminDao {
    Optional<Admin> getAdminById(String id);
    List<Admin> getAllAdmins();
    Admin add(Admin admin);
    Admin update(Admin admin, String id);
    void delete(String id);
}
