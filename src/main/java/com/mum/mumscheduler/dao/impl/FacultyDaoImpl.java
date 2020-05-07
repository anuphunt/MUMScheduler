package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IFacultyDao;
import com.mum.mumscheduler.models.Faculty;
import com.mum.mumscheduler.respository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.List;
import java.util.Optional;


@Configuration
public class FacultyDaoImpl implements IFacultyDao {

    @Autowired
    private FacultyRepository facultyRepository;

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
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Faculty faculty, String id){
        if(id!=null)
            return facultyRepository.save(faculty);
        else
            return null;
    }

    @Override //Don't need to return anything for delete operation
    public void delete(String id){
        facultyRepository.deleteById(id);
    }


}
