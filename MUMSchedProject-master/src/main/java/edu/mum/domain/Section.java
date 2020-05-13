package edu.mum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String sectionCode="TEST-A";
	private int limitCapacity=25;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Course course;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "block_id")
	private Block block;

	@ManyToMany(mappedBy = "sections", cascade = CascadeType.PERSIST)
	List<Student> students = new ArrayList<Student>();

	public Section() {
	}

	public Section(Block block) {
		this.block = block;

	}

	public Section(Block block, Course course) {
		this.block = block;
		this.course = course;

	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getLimitCapacity() {
		return limitCapacity;
	}

	public void setLimitCapacity(int limitCapacity) {
		this.limitCapacity = limitCapacity;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	

}
