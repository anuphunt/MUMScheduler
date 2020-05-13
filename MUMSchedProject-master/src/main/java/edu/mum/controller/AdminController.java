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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.mum.domain.Admin;
import edu.mum.service.AdminService;
import edu.mum.service.RoleService;


@Controller

public class AdminController {

		@Autowired
		AdminService adminService;
		@Autowired
		RoleService roleService;
	   
	   @PreAuthorize("hasAnyRole('ROLE_Admin')")
	   @GetMapping(value = "/admin/admin/add")
	   public String addUser(@ModelAttribute("newAdmin") Admin admin, Model model) {
	   model.addAttribute("userTypeList", roleService.getAll());
	        return "addAdmin";
       }
	   @PreAuthorize("hasAnyRole('ROLE_Admin')")
	   @RequestMapping(value = "/admin/admin/add", method = RequestMethod.POST)
	   public String saveUser(@Valid @ModelAttribute("newAdmin") Admin admin, BindingResult error,Model model){
		
		if (error.hasErrors()) {
			if(!model.containsAttribute("userTypeList")){
			model.addAttribute("userTypeList",roleService.getAll());
			return "addAdmin";
			}
		}
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		admin.getUserProfile().setPassword(passwordEncoder.encode(admin.getUserProfile().getPassword()));
		 adminService.save(admin);
		return "redirect:/admin/admin/all";
	   }
	   
	    @PreAuthorize("hasAnyRole('ROLE_Admin')")
	    @GetMapping(value = "admin/admin/all")
	    public String getAllUser(Model model) {
		model.addAttribute("adminList", adminService.getAll());
	
		return "manageAdmin";
	   }
	   @PreAuthorize("hasAnyRole('ROLE_Admin')")
	   @GetMapping(value = "admin/admin/delete/{id}")
	    public String userMarkDelete(@PathVariable("id") Long id, Model model) {
		Admin admin = adminService.getAdminById(id);
		admin.getUserProfile().setUserStatus("Deleted");
		adminService.save(admin);
		return "redirect:/admin/admin/all";
	   }
	   @PreAuthorize("hasAnyRole('ROLE_Admin')")
	    @GetMapping(value = "admin/admin/update/{id}")
	    public String updateUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
		System.out.println("id" + id);
		redirect.addFlashAttribute("editAdmin", adminService.getAdminById(id));
		     return "redirect:/admin/admin/all";
	    }
	    
	    @GetMapping(value = "admin/admin/update/")
	    public String saveUpdateAdmin(@ModelAttribute("editAdmin")Admin admin,BindingResult error,Model model) {
	    	if (error.hasErrors()) {
				if(!model.containsAttribute("userTypeList"))
				model.addAttribute("userTypeList",roleService.getAll());
				return "editAdmin";
				}
				admin.getUserProfile().setId(admin.getUserProfile().getId());
				adminService.save(admin);
		     return "redirect:/admin/admin/all";
	    }
	  }	  


	