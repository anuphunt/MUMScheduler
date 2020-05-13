package com.mum.mumscheduler.utilities.section;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.models.Section;

import java.util.List;

public interface ISectionCreator {
    List<Section> createSections(Block block);
}
