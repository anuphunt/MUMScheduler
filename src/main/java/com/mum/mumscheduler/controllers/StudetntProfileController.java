package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudetntProfileController {
    @Autowired
    IStudentService studentProfileService;

    @GetMapping("/studentprofile/{username}")
    public String showProfile(@PathVariable String username, Model model)
    {
        model.addAttribute("student",studentProfileService.getStudentByUsername(username));
        return "frontend/student/main";
    }
    @GetMapping("/studentprofile/details/{username}")
    public String showProfiledetails(@PathVariable String username, Model model)
    {
        model.addAttribute("student",studentProfileService.getStudentByUsername(username));
        return "frontend/student/studentprofile";
    }

}
