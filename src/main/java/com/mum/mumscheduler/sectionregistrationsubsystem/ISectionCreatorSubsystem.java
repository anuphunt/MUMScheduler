package com.mum.mumscheduler.sectionregistrationsubsystem;

import com.mum.mumscheduler.models.*;
import com.mum.mumscheduler.services.impl.StudentServiceImpl;

import java.util.List;

public interface ISectionCreatorSubsystem {
    List<Section> createSections(Block block);
    void registerStudentsInSection(List<Student> students, Section section);
}
