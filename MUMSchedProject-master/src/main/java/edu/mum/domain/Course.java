package edu.mum.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;


  @Entity
    public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private int courseCode;
	@NotNull
	private String courseName;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "course_preReqCourse")
	private List<Course> prerequisite;
	private Boolean isPreReq;
	private String courseDesc;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Specialization> courseArea;

	public Boolean getIsPreReq() {
		return isPreReq;
	}

	public void setIsPreReq(Boolean isPreReq) {
		this.isPreReq = isPreReq;
	}

	public String getCourseName() {
		return courseName;

	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	

	public Set<Specialization> getCourseArea() {
		return courseArea;
	}

	public void setCourseArea(Set<Specialization> courseArea) {
		this.courseArea = courseArea;
	}

	public List<Course> getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(List<Course> prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}


}
