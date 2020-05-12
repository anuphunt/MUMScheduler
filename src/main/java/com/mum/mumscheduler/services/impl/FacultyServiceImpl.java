package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.IFacultyDao;
import com.mum.mumscheduler.models.Faculty;
import com.mum.mumscheduler.services.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements IFacultyService {

    //Getting dependencies from IFacultyDao
    @Autowired
    private IFacultyDao facultyRepository;

    @Override
    public Optional<Faculty> getFacultyById(String id){
        return facultyRepository.getFacultyById(id);
    }

    @Override
    public List<Faculty> getAllFaculty(){
        return facultyRepository.getAllFaculty();
    }

    @Override
    public Faculty add(Faculty faculty){
        return facultyRepository.add(faculty);
    }

    @Override
    public Faculty update(Faculty faculty, String id){
        return facultyRepository.update(faculty, id);
    }

    @Override //Don't need to return anything for delete operation
    public void delete(String id){
        facultyRepository.delete(id);
    }
}
