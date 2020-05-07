package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Admin;
import com.mum.mumscheduler.services.IAdminService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/addadmin")
    public String getAddAdminForm(Model model) {
        Admin admin=new Admin();
        model.addAttribute("admin", admin);
        return "admin/addadminform";
    }

    @RequestMapping(value = "/addadmin", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.add(admin);
        return "redirect:/admin/all";
    }

    @GetMapping("/all")
    public String showAllAdmins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin/showalladmin";
    }

    @GetMapping("/updateadmin/{id}")
    public String showUpdateForm(Model model, @PathVariable String id){
        Admin admin = adminService.getAdminById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Admin Id: " + id));
        model.addAttribute("admin", admin);
        return "admin/addadminform";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") String id, Model model) {
        Admin admin = adminService.getAdminById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        adminService.delete(admin.getId());
        model.addAttribute("admins", adminService.getAllAdmins());
        return "redirect:/admin/all";
    }
}
