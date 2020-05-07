package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Faculty;

import java.util.List;
import java.util.Optional;

public interface IFacultyDao {
    Optional<Faculty> getFacultyById(String id);
    List<Faculty> getAllFaculty();
    void delete(String id);
    Faculty add(Faculty faculty);
    Faculty update(Faculty faculty, String id);
}
