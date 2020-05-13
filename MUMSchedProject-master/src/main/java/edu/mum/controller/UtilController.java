package edu.mum.controller;

import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Role;
import edu.mum.domain.Specialization;
import edu.mum.service.RoleService;
import edu.mum.service.SpecializationsService;

@Controller
@RequestMapping("/admin")
public class UtilController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	SpecializationsService specialService;
	
	@PreAuthorize("hasAnyRole('ROLE_Admin')")
	@GetMapping("/role/add")
	public String addRole(@ModelAttribute("newRole") Role role)
	{
		
      return "addRole";
	}
	
	 @PreAuthorize("hasAnyRole('ROLE_Admin')")
	@PostMapping("/role/add")
	public String saveRole(@Valid @ModelAttribute("newRole") Role role,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addRole";
		}
        roleService.save(role);
        return "redirect :/admin/role/all";
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_Admin')")
	@GetMapping("/role/all")
	public String allRole(Model model)
	{
	  model.addAttribute("roles",roleService.getAll());
      return "manageRole";
	}
	

	   @PreAuthorize("hasAnyRole('ROLE_Admin')")
	    @GetMapping(value = "/role/update/{id}")
	    public String updateRole(@PathVariable("id") Long id, Model model) {
		 model.addAttribute("editedRole",roleService.findOne(id));
		
		     return "editRole";
	    }
	    
	    @PreAuthorize("hasAnyRole('ROLE_Admin')")
	     @PostMapping(value ="/role/update")
	    public String saveEditedRole(@Valid @ModelAttribute("editedRole")Role role,BindingResult error) {
         System.out.println("Role");
		   if(error.hasErrors()){
        	   return "editRole";
          }
        
         System.out.println("role id"+role.getId());
         roleService.save(role);
		     return "redirect:/admin/role/all";
	    }
	  
	@GetMapping(value = "/role/delete/{id}")
		public String deleteFaculty(@PathVariable("id") Long id, Model model) {
			roleService.deleteRole(id);

			return "redirect:/admin/role/all";
		}
	@GetMapping("/special/add")
	public String addSpecial(@ModelAttribute("newSpecial") Specialization special)
	{
	 
      return "addSpecial";
	}
	
	@PostMapping("/special/add")
	public String saveSpecail(@ModelAttribute("newSpecial") Specialization special,BindingResult error)
	{
		if(error.hasErrors())
		{
			return "addSpecial";
		}
        specialService.save(special);
        return "redirect:/admin/special/all";
		
	}
	
	@GetMapping("/special/all")
	public String allSpecial(Model model)
	{
	  model.addAttribute("SpecialList", specialService.findAllspecalization());
      return "manageSpecial";
	}

	@GetMapping(value = "/specialization/delete/{id}")
	public String deleteSpecialization(@PathVariable("id") Long id, Model model) {
		specialService.deleteSpecialization(id);

		return "redirect:/admin/special/all";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_Admin')")
    @GetMapping(value = "/specialization/update/{id}")
    public String updateSpecialization(@PathVariable("id") Long id, Model model) {
	 model.addAttribute("editedSpecial",specialService.getSpecialization(id));
	
	     return "editSpecial";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_Admin')")
     @PostMapping(value ="/specialization/update")
    public String saveEditedSpecialization(@Valid @ModelAttribute("editedSpecial")Specialization special,BindingResult error) {
     
	   if(error.hasErrors()){
    	   return "editRole";
      }
	   specialService.save(special);
	   return "redirect:/admin/special/all";
    }
    
}
