package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IFacultyDao;
import com.mum.mumscheduler.models.Faculty;
import com.mum.mumscheduler.models.User;
import com.mum.mumscheduler.respository.FacultyRepository;
import com.mum.mumscheduler.respository.UserRepository;
import com.mum.mumscheduler.utilities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.List;
import java.util.Optional;


@Configuration
public class FacultyDaoImpl implements IFacultyDao {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Faculty> getFacultyById(String id){
        return facultyRepository.findById(id);
    }

    @Override
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    @Override
    public Faculty add(Faculty faculty){
        Faculty faculty1 = facultyRepository.save(faculty);
        userRepository.save(new User(faculty.getFacultyId(), faculty.getUsername(), faculty.getPassword(), UserRole.FACULTY));
        return faculty1;
    }

    @Override
    public Faculty update(Faculty faculty, String id){
        if(id!=null){
            Faculty faculty1 = facultyRepository.save(faculty);
            userRepository.save(new User(faculty1.getFacultyId(), faculty1.getUsername(), faculty1.getPassword(), UserRole.FACULTY));
            return faculty1;
        }
        else
            return null;
    }

    @Override //Don't need to return anything for delete operation
    public void delete(String id){
        facultyRepository.deleteById(id);
        userRepository.deleteById(id);
    }


}
