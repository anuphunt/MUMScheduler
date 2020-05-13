package com.mum.mumscheduler.sectionregistrationsubsystem;

import com.mum.mumscheduler.dao.ICourseDao;
import com.mum.mumscheduler.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SectionCreatorSubsystemFacade implements ISectionCreatorSubsystem {

    @Autowired
    ICourseDao courseDao;

    @Override
    public List<Section> createSections(Block block) {
        List<Section> sections = new ArrayList<>();

        int requiredFPPSections = block.getFPPNum()/25;
        int requiredMPPSections = block.getMPPNum()/25;

        for(int i = 0; i<requiredFPPSections; i++){
            Course course = new Course("343", "FPP", null, "2020-02-19", "2020-03-19");
            sections.add(new Section(course, block, new ArrayList<Student>()));
        }
        for(int i = 0; i<requiredMPPSections; i++){
            Course course = new Course("344", "MPP", Arrays.asList("FPP"), "2020-02-19", "2020-03-19");
            sections.add(new Section(course, block, new ArrayList<Student>()));
        }

        return new ArrayList<Section>();
    }

    @Override
    public void registerStudentsInSection(List<Student> students, Section section) {

    }
}
