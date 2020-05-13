package edu.mum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.mum.domain.Section;
import edu.mum.domain.Student;

public interface SectionRepository extends CrudRepository<Section, Long>{

		public List<Student> findStudentById(Long id);
		public List<Section> findByFacultyId(long id);
}
