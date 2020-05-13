package edu.mum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.mum.domain.Block;
import edu.mum.domain.Course;
import edu.mum.domain.Section;
import edu.mum.domain.Student;
import edu.mum.repository.SectionRepository;

@Service 
public class SectionsService {
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private BlockService blockService;

	public void saveSection(Section section) {	
		sectionRepository.save(section);
	}

	public List<Section> getAllSection() {
		return (List<Section>) sectionRepository.findAll();
	}

	public Section getSectionById(Long id) {
		return sectionRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
	}

	
	public void deleteSection(Long id) {
		sectionRepository.deleteById(id);
	}

	
	public void updateSection(Section section) {
		sectionRepository.save(section);
		
	}
	
	public Section createSection(Long block_id) {
		Section section = new Section();
		Block block = blockService.getBlockById(block_id);
		section.setBlock(block);
		return section;
	}
	public Section createSection(Long block_id, Course course) {
		Section section = new Section();
		Block block = blockService.getBlockById(block_id);
		section.setCourse(course);
		section.setBlock(block);
		return section;
	}
	
	public List<Student> getStudentBySection(Long id){
		return (List<Student>) sectionRepository.findStudentById(id);
	}
	
	public List<Section> getSectionsForFaculty(long facultyId){
		return sectionRepository.findByFacultyId(facultyId);
	}
}
