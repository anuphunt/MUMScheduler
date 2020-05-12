package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.IAdminDao;
import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Admin;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IAdminService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {


    @Autowired
    private IAdminDao dao;

    @Override
    public Optional<Admin> getAdminById(String id) {
        return dao.getAdminById(id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return dao.getAllAdmins();
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public Admin add(Admin admin) {
        return dao.add(admin);
    }

    @Override
    public Admin update(Admin admin, String id) {
        return dao.update(admin, id);
    }
}
