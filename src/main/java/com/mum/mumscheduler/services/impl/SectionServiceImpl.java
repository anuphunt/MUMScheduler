package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.ISectionDao;
import com.mum.mumscheduler.models.Section;
import com.mum.mumscheduler.services.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements ISectionService {

    @Autowired
    private ISectionDao sectionRepository;

    @Override
    public Optional<Section> getSectionById(String id){
        return sectionRepository.getSectionById(id);
    }

    @Override
    public List<Section> getAllSection(){
        return sectionRepository.getAllSection();
    }

    @Override
    public Section add(Section section){
        return sectionRepository.add(section);
    }

    @Override
    public Section update(Section section, String id){
        return sectionRepository.update(section, id);
    }

    @Override //Don't need to return anything for delete operation
    public void delete(String id){
        sectionRepository.delete(id);
    }


}
