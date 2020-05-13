package com.mum.mumscheduler.dao;


import com.mum.mumscheduler.models.Section;

import java.util.List;
import java.util.Optional;

public interface ISectionDao {
    Optional<Section> getSectionById(String id);
    List<Section> getAllSection();
    Section add(Section section);
    Section update(Section section, String id);
    void delete(String id);
}
