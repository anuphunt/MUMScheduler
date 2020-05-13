package edu.mum.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;


@Entity
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Specialization> specializations=new ArrayList<>();
	@ManyToMany(cascade=CascadeType.ALL)
	@Valid
	private List<Course> course=new ArrayList<>();
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Valid
	private UserProfile userProfile;
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="faculty")
	private List<Section> sections=new ArrayList<Section>();
	private boolean isAvailability;

	public Faculty(String userProfile) {
		super();
		this.userProfile = new UserProfile(userProfile);
	}
	public Faculty() {
		
	}
	public List<Specialization> getSpecializations() {
		return specializations;
	}
	public void setSpecializations(List<Specialization> specializations) {
		this.specializations = specializations;
	}
	public List<Course> getCourse() {
		return course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public boolean isAvailability() {
		return isAvailability;
	}
	public void setAvailability(boolean isAvailability) {
		this.isAvailability = isAvailability;
	}
	public void addSpecalization(Specialization specialization)
	{
		specializations.add(specialization);
	}
	public void addCourse(Course course)
	{
		this.course.add(course);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		return true;
	}
	
}
