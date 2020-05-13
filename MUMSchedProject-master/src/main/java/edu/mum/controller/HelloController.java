package edu.mum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Course;
import edu.mum.service.CourseService;


@Controller
public class HelloController {
    
	/*@Autowired
	CourseService  courserService;
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String Hello(@ModelAttribute("course")Course courseToBeSaved) {
	
		return "ManageCourse";
	}*/
	
	/*@RequestMapping(value="/add",method=RequestMethod.POST)
	public String save(@ModelAttribute("course")Course courseToBeSaved,RedirectAttributes redirect) {
	    courserService.save(courseToBeSaved);
	    redirect.addFlashAttribute("newCourse",courseToBeSaved);
		return "redirect:/sucess";
	}*/
	
	@RequestMapping(value="/sucess")
	public String  courseDetail()
	{
		return "courseDetail";
	}
	/*@RequestMapping(value="/login", method=RequestMethod.POST)
	  public String handleRequest3(ModelMap map) {
	      map.addAttribute("time", LocalDateTime.now().toString());
	      return "home";
	}*/
}
