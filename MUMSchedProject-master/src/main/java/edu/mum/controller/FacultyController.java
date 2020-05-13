package edu.mum.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.domain.Specialization;
import edu.mum.service.CourseService;
import edu.mum.service.FacultyService;
import edu.mum.service.RoleService;
import edu.mum.service.SectionsService;
import edu.mum.service.SpecializationsService;
import edu.mum.service.UserProfileService;

@Controller
public class FacultyController {
	Specialization spe;
	@Autowired
	FacultyService facultyService;
	@Autowired
	RoleService roleService;
	@Autowired
	SpecializationsService specializationsService;
	@Autowired
	CourseService courseService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	private SectionsService sectionService;

	// only admin can add new Faculty

	@PreAuthorize("hasAnyRole('ROLE_Admin')")
	@GetMapping(value = "/admin/faculty/add")
	public String addFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model) {
		model.addAttribute("userTypeList", roleService.getAll());
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList", courseService.getAllCourser());
		return "addFaculty";
	}

	@PreAuthorize("hasAnyRole('ROLE_Admin')")
	@PostMapping(value = "/admin/faculty/add")
	public String saveFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty, BindingResult error, Model model) {
		System.out.println("admin add");
		if (error.hasErrors()) {
			if (!model.containsAttribute("specializations")) {
				model.addAttribute("specializations", specializationsService.findAllspecalization());
			}
			if (!model.containsAttribute("userTypeList")) {
				model.addAttribute("userTypeList", roleService.getAll());
			}
			if (!model.containsAttribute("courseList")) {
				model.addAttribute("courseList", courseService.getAllCourser());
			}

			return "adminEditFaculty";
		}

		System.out.println("before");
		faculty.getUserProfile().setUserStatus("Active");
		System.out.println("faculty" + faculty.getUserProfile().getFirstName());
		
		System.out.println(faculty.getCourse().get(1));
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		faculty.getUserProfile().setPassword(passwordEncoder.encode(faculty.getUserProfile().getPassword()));

		System.out.println("password string:  " + passwordEncoder.encode(faculty.getUserProfile().getPassword()));
		
		facultyService.saveFaculty(faculty);

		return "redirect:/admin/faculty/all";
	}
	

	@GetMapping(value = "/admin/faculty/delete/{id}")
	public String deleteFaculty(@PathVariable("id") Long id, Model model) {
		facultyService.deleteFaculty(id);

		return "redirect:/admin/faculty/all";
	}

	@GetMapping(value = "/admin/faculty/update/{id}")
	public String userMarkDelete(@PathVariable("id") long id, Model model) {
		model.addAttribute("newFaculty", facultyService.getFacultyById(id));
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList", courseService.getAllCourser());
		model.addAttribute("userTypeList", roleService.getAll());
		return "adminEditFaculty";
	}

	@PostMapping(value = "/admin/faculty/edit")
	public String editFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty, BindingResult error, Model model) {
		if (error.hasErrors()) {
			if (!model.containsAttribute("specializations")) {
				model.addAttribute("specializations", specializationsService.findAllspecalization());
			}
			if (!model.containsAttribute("userTypeList")) {
				model.addAttribute("userTypeList", roleService.getAll());
			}
			if (!model.containsAttribute("courseList")) {
				model.addAttribute("courseList", courseService.getAllCourser());
			}

			return "adminEditFaculty";
		}


		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		faculty.getUserProfile().setPassword(passwordEncoder.encode(faculty.getUserProfile().getPassword()));
        faculty.setUserProfile(faculty.getUserProfile());
		facultyService.saveFaculty(faculty);
		return "redirect:/admin/faculty/all";
	}

	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping(value = "/admin/faculty/all")
	public String ManageStudent(Model model) {
		model.addAttribute("faculties", facultyService.getAllfaculty());
		return "manageFaculty";
	}

	

	@GetMapping(value = "/nav")
	public String nav(Model model) {

		return "sidebar";
	}
    
	
	@GetMapping("/faculty/update")
	public String updateFacultyProfile( Model model) {
		 System.out.println(userProfileService.LoggedInUser().getFirstName());
		Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
        System.out.println(userProfileService.LoggedInUser().getFirstName());
		model.addAttribute("newFaculty", faculty);
		model.addAttribute("facultySpecialList", faculty.getSpecializations());
		model.addAttribute("facultyCourseList", faculty.getCourse());
		model.addAttribute("specializations", specializationsService.findAllspecalization());
		model.addAttribute("courseList", courseService.getAllCourser());
		/*List<Section> facultySection = sectionService.getAllSection().stream()
				.filter(s -> s.getFaculty().equals(faculty)).collect(Collectors.toList());*/
		if(!faculty.getSections().isEmpty()){
		List<Section> facultySection = sectionService.getSectionsForFaculty(faculty.getId());
		model.addAttribute("sections", facultySection);}
		

		return "editFaculty";

	}
    
	
	@PostMapping("/faculty/updatePersonaInfo")
	public String saveUpdateFacultyProfile(@Valid @ModelAttribute("newFaculty") Faculty editedFaculty,
			BindingResult error, Model model) {
		System.out.println("POST");
		if (error.hasErrors()) {

			return "editFaculty";
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		faculty.getUserProfile().setUserName(editedFaculty.getUserProfile().getUserName());
		faculty.getUserProfile().setPassword(passwordEncoder.encode(editedFaculty.getUserProfile().getPassword()));
		faculty.getUserProfile().setEmail(editedFaculty.getUserProfile().getEmail());
		facultyService.saveFaculty(faculty);
		return "redirect:/home";

	}
	@PreAuthorize("hasRole('ROLE_Faculty')")
	@PostMapping("/faculty/updateSpecialization")
	public String updateSpecialization(@Valid @ModelAttribute("newFaculty") Faculty editedFaculty, BindingResult error,
			Model mode) {

		Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		faculty.addSpecalization(editedFaculty.getSpecializations().get(0));
		facultyService.saveFaculty(faculty);
		System.out.println("Saved");
		return "redirect:/faculty/update";

	}
	@PreAuthorize("hasRole('ROLE_Faculty')")
	@PostMapping("/faculty/updateCourse")
	public String updateCourse(@Valid @ModelAttribute("newFaculty") Faculty editedFaculty, BindingResult error,
			Model mode) {

		Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		faculty.addCourse(editedFaculty.getCourse().get(0));
		facultyService.saveFaculty(faculty);
		System.out.println("Saved");
		return "redirect:/faculty/update";

	}
	
	@GetMapping("/faculty/viewSchedule")
	public String viewFacultySchedule(Model model) {
		Faculty faculty = facultyService.getFacultyByUserProfile(userProfileService.LoggedInUser());
		List<Section> facultySection = sectionService.getAllSection().stream()
				.filter(s -> s.getFaculty().equals(faculty)).collect(Collectors.toList());
		model.addAttribute("sections", facultySection);
		model.addAttribute("newFaculty",faculty);
		return "viewFacultySchedule";
	}
	
	@PreAuthorize("hasRole('ROLE_Faculty')")
	@GetMapping("/faculty/section/grading/{id}")
	public String gradeStudents(@PathVariable("id") long id, Model model){
		System.out.println(id);
		model.addAttribute("studentList", sectionService.getSectionById(id).getStudents());
		model.addAttribute("course", sectionService.getSectionById(id).getCourse());
		return "gradeStudents";
	}
	
	/*@PostMapping("/faculty/section/grading")
	public String studentsGradded(@ModelAttribute("studentList") List<Student> students){
		return "";
	}*/

}
