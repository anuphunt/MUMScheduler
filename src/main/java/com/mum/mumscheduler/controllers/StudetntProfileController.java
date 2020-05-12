package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Student;
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

    @GetMapping("/studentprofile/{id}")
    public String showProfile(@PathVariable String id, Model model)
    {
        model.addAttribute("student",studentProfileService.getStudentByUsername("foo"));
        return "frontend/student/studentprofile";
    }



}
