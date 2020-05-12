package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.ISectionDao;
import com.mum.mumscheduler.models.Section;
import com.mum.mumscheduler.respository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class SectionDaoImpl implements ISectionDao {

    @Autowired
    private SectionRepository sectionRepository;

    public Optional<Section> getSectionById(String id){
        return sectionRepository.findById(id);
    }

    public List<Section> getAllSection(){
        return sectionRepository.findAll();
    }

    public Section add(Section section){
        return sectionRepository.save(section);
    }

    public Section update(Section section, String id){
        if(id!=null){
            return sectionRepository.save(section);
        }
        else return null;
    }

    public void delete(String id){
        sectionRepository.deleteById(id);
    }

}

