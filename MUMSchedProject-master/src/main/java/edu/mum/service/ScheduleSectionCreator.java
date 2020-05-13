package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Section;
import edu.mum.repository.CourseRepository;

@Service
public class ScheduleSectionCreator {

	@Autowired
	private CourseRepository courseDao;
	private List<Course> courses;
	private Course FPP = new Course();
	private Course MPP = new Course();
	private Course SCI = new Course();

	public void createSectionForBlock(Block block) {
		courses = (List<Course>) courseDao.findAll();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourseName().equalsIgnoreCase("FPP"))
				this.FPP = courses.get(i);
			if (courses.get(i).getCourseName().equalsIgnoreCase("MPP"))
				this.MPP = courses.get(i);
			if (courses.get(i).getCourseName().equalsIgnoreCase("SCI"))
				this.SCI = courses.get(i);
		}
		switch (block.getBlockOrder()) {
		case 0:
			zeroBlock(block);
			break;
		case 1:
			firstBlock(block);
			break;
		case 2:
			secondBlock(block);
			break;
		default:
			otherBlocks(block);
		}

	}

	private void otherBlocks(Block block) {
		int noTotalStudents = block.getNumOfStudents();
		for (int i = 0; i < (noTotalStudents / 25.0); i++) {
			block.getSections().add(new Section(block));
		}
	}

	private void secondBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for (int i = 0; i < noFPP / 25; i++) {
			// System.out.println("=====ScheduleSectionCreator secondBlock
			// method"+block.getSections().get(0).getLimitCapacity());
			block.getSections().add(new Section(block));
		}
		int noMPP = block.getEntry().getNumOfMpp();
		for (int i = 0; i < noMPP / 25; i++) {
			block.getSections().add(new Section(block));
		}
	}

	private void firstBlock(Block block) {
		int noFPP = block.getEntry().getNumOfFpp();
		for (int i = 0; i < (noFPP / 25); i++) {
			block.getSections().add(new Section(block));
		}
		int noMPP = block.getEntry().getNumOfMpp();
		for (int i = 0; i < (noMPP / 25); i++) {
			block.getSections().add(new Section(block));
		}
	}

	private void zeroBlock(Block block) {
		block.getSections().add((new Section(block, this.SCI)));
	}
}
