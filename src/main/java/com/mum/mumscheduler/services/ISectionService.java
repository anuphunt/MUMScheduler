package com.mum.mumscheduler.services;


import com.mum.mumscheduler.models.Section;

import java.util.List;
import java.util.Optional;

public interface ISectionService {
    Optional<Section> getSectionById(String id);
    List<Section> getAllSection();
    Section add(Section section);
    Section update(Section section, String id);
    void delete(String id);
}
