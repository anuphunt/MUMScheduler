package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.domain.Course;
import edu.mum.service.CourseService;
import edu.mum.service.SpecializationsService;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
 
	@Autowired
	CourseService courseService;
	
	@Autowired
	SpecializationsService specializationsService;

	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping("/all")
	public String courseList( @ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courses",courseService.getAllCourser());
		model.addAttribute("noPre","  ");
		return "manageCourse";
		
	}
	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping("/add")
	public String addCourse(@ModelAttribute("newCourse") Course course,Model model)
	{
		model.addAttribute("courseList",courseService.getAllCourser());
		model.addAttribute("areaList",specializationsService.findAllspecalization());
		return "addCourse";
		
	}
	
	@PostMapping("/add")
	public String saveCourse(@Valid @ModelAttribute("newCourse") Course course,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			if(!model.containsAttribute("courseList"))
				model.addAttribute("courseList",courseService.getAllCourser());
			if(!model.containsAttribute("areaList"))
				model.addAttribute("areaList",specializationsService.findAllspecalization());
			return "addCourse";
		}
		
		course.setIsPreReq(false);
		if(course.getPrerequisite()!=null){
			course.getPrerequisite().forEach(c->c.setIsPreReq(true));
		}
		courseService.save(course);
		
		return "redirect:/admin/course/all";
		
	}
	
	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping(value = "/update/{id}")
	public String editCourse(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editedCourse", courseService.getCourseById(id));
		model.addAttribute("courseList",courseService.getAllCourser());
		model.addAttribute("areaList",specializationsService.findAllspecalization());
		return "editCourse";
	}
	
	@PreAuthorize("hasRole('ROLE_Admin')")
	@PostMapping(value = "/update")
	public String saveEditCourse(@Valid @ModelAttribute("editedCourse") Course course,BindingResult result,Model model) {
		if(result.hasErrors())
		{
			if(!model.containsAttribute("courseList"))
				model.addAttribute("courseList",courseService.getAllCourser());
			if(!model.containsAttribute("areaList"))
				model.addAttribute("areaList",specializationsService.findAllspecalization());
			return "editCourse";
		}
		courseService.save(course);
		return "redirect:/admin/course/all";
	}
	
	
	
}
