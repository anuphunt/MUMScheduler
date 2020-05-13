package edu.mum.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import edu.mum.domain.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.registersubsystem.impl.RegisterSubsystemFacade;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.EntryService;
import edu.mum.service.RoleService;
import edu.mum.service.ScheduleService;
import edu.mum.service.SectionsService;
import edu.mum.service.StudentService;
import edu.mum.service.UserProfileService;



@Controller
public class StudentRegController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	UserProfileService userprofileService;
	@Autowired
	RoleService roleService;
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ScheduleRepository schedulerep;
	@Autowired
	RegisterSubsystemFacade regsubsystem;
	@Autowired
	SectionsService sectionservice;
	@Autowired
	EntryService entryService;
	@Autowired
	private ScheduleRepository scheduleDao;

	
	
	@GetMapping(value = "/student")
	public String studentRegForm(Model model) {
		 UserProfile userProfile = userprofileService.LoggedInUser();
		
		Student student= studentService.getStudentByUserProfile(userProfile);
		 model.addAttribute("student",student);
		 
   	    return "studentmainpage";
    }
	 
	 @RequestMapping(value={"/student/viewschedule"},method=RequestMethod.GET)
		public String studentschedule(Model model) {
		 
		 UserProfile userProfile = userprofileService.LoggedInUser();
		 Student student = studentService.getStudentByUserProfile(userProfile);
		Schedule schedule = scheduleService.getScheduleByEntryId(student.getEntry().getId());
		// System.out.println("=======> schduel id "+schedule.getId());
		 
		 model.addAttribute("blocks", schedule.getEntry().getBlocks());
			model.addAttribute("entry",schedule.getEntry());
				
	//	model.addAttribute("blocks", schedule.getEntry().getBlocks());
		//model.addAttribute("entry",schedule.getEntry());
	
			
	   	    return "viewSchedule";
	    }
	
	    @PreAuthorize("hasRole('ROLE_Admin')")
		@GetMapping(value = "admin/students")
		public String ManageStudent(Model model) {
			model.addAttribute("students", studentService.getAllstudents());
			return "managestudent";
		}
	 
	 @RequestMapping(value={"/student/register"},method=RequestMethod.GET)
		public String registerstudent(Model model) {
		 Student student = studentService.getStudentByUserProfile(userprofileService.LoggedInUser());
		 	//System.out.println("logged User:"+userprofileService.LoggedInUser().getFirstName());
	    List<Section> sections = regsubsystem.getListSection();
	    List<Long> ids = student.getSections().stream().map(s->s.getId()).collect(Collectors.toList());
	    
	    List<Section> regsections = sections.stream().filter(s-> !(ids.contains(s.getId()))).collect(Collectors.toList());
	    Schedule schedule = scheduleDao.findScheduleByEntryId(student.getEntry().getId());

	    model.addAttribute("blocks", schedule.getEntry().getBlocks());
	    
		model.addAttribute("sections",regsections );
		
	   	    return "studentregister";
	    }
	 
	    @PreAuthorize("hasRole('ROLE_Admin')")
	    @GetMapping(value = "admin/addstudent")
		public String addstudent(@ModelAttribute("Newstudent") Student student, Model model) {
			model.addAttribute("userTypeList", roleService.getAll());
			
			List<Entry> entries = entryService.getAllEntry();
		//	for(Entry entry: entries)
				//System.out.println(entry.getEntryMonth());
			model.addAttribute("entries", entries);
			
			return "addstudent";
		}

		@PostMapping(value = "admin/addstudent")
		public String savestudent(@Valid @ModelAttribute("Newstudent") Student student, BindingResult error,
				RedirectAttributes redirect, Model model) {
			//System.out.println(student.getEntry());
			List<Entry> entries = entryService.getAllEntry();
		/*	for(Entry entry: entries)
				System.out.println(entry.getEntryMonth());*/
			model.addAttribute("entries", entries);
			
			/*if (error.hasErrors())

			{
				return "addstudent";
			}*/
			

			System.out.println("before");
			student.getUserprofile().setUserStatus("Active");
			//System.out.println("student" + student.getUserprofile().getFirstName());
//
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			student.getUserprofile().setPassword(passwordEncoder.encode(student.getUserprofile().getPassword()));

//			System.out.println("password string:  " + passwordEncoder.encode(student.getUserprofile().getPassword()));
			studentService.save(student);
			return "redirect:/admin/students";
		}
	 
	 @RequestMapping(value= {"student/updatestudent"},method=RequestMethod.GET)
		public String updateStudent(Model model){
				UserProfile userProfile = userprofileService.LoggedInUser();
				
					Student student = studentService.getStudentByUserProfile(userProfile);
				 
		 model.addAttribute("Newstudent", student);
		
			//studentService.save(student);
			 return "editstudent";
		}
	 
	 @RequestMapping(value= {"student/updatestudent"},method=RequestMethod.POST)
		public String updatedStudent(@ModelAttribute("Newstudent") Student Newstudent,Model model){
		 System.out.println("=======updateStudent Controller");
				UserProfile userProfile = userprofileService.LoggedInUser();
				
					Student student = studentService.getStudentByUserProfile(userProfile);
					//System.out.println("=======updateStudent Controller"+Newstudent.getUserprofile().getUserName());
					student.getUserprofile().setUserName(Newstudent.getUserprofile().getUserName());
					
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					student.getUserprofile().setPassword(passwordEncoder.encode(Newstudent.getUserprofile().getPassword()));
					
					student.getUserprofile().setEmail(Newstudent.getUserprofile().getEmail());
				 
		 //model.addAttribute("Newstudent", student);
		
			studentService.save(student);
			model.addAttribute("student", student);
			 return "studentmainpage";
		}
	 

	
	@RequestMapping(value={"/student/register/{id}"}, method = RequestMethod.GET)
    public String registerStudent( @PathVariable Long id,  /*BindingResult bindingresult,*/ Model model/*,RedirectAttributes redirAttrs*/) {
		
		/*if(bindingresult.hasErrors()){
			return "studentregister";
		}*/
		UserProfile userProfile = userprofileService.LoggedInUser();
		String str = regsubsystem.register(sectionservice.getSectionById(id));
		
		
		if(!(str.equalsIgnoreCase("Success"))){
			//redirAttrs.addFlashAttribute("message", str);
			model.addAttribute("message",str);
		return "errorregister";
	}
		model.addAttribute("message", str);
		
		
		//redirAttrs.addFlashAttribute("message", str);
		
		model.addAttribute("sections",studentService.getStudentByUserProfile(userProfile).getSections());
		
		//redirAttrs.addAttribute("sections",studentService.getStudentByUserProfile(userProfile).getSections());
	
		
		//return "redirect:/student";
   	return "addsuccess";
    }
	
	//Admin
	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping(value = "admin/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		studentService.deleteStudent(id);

		return "redirect:/admin/students";
	}
	
	@PreAuthorize("hasRole('ROLE_Admin')")
	@GetMapping(value = "admin/student/update/{id}")
	public String updateStudent(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editStudent", studentService.getStudentById(id));
		
		List<Entry> entries = entryService.getAllEntry();
		
		model.addAttribute("entries", entries);
		model.addAttribute("userTypeList", roleService.getAll());
		return "adminEditStudent";
	}
	
	@PreAuthorize("hasRole('ROLE_Admin')")
	@PostMapping(value = "admin/student/update")
	public String saveUpdateStudent(@Valid @ModelAttribute("editStudent")Student editedstudent,BindingResult error,Model model) {
		
		if(error.hasErrors()){
			
			if(!model.containsAttribute("entries"))
				model.addAttribute("entries", entryService.getAllEntry());
		    if(!model.containsAttribute("userTypeList"))
		    		model.addAttribute("userTypeList", roleService.getAll());
		   return "adminEditStudent";
		}
		//editedstudent.setId(editedstudent.getUserprofile().getId());
		//editedstudent.getUserprofile().setId(editedstudent.getUserprofile().getId());
		studentService.save(editedstudent);
		
		return "redirect:/admin/students";
	}
}
	

