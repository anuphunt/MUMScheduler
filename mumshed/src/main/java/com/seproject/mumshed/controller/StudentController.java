package com.seproject.mumshed.controller;


import com.seproject.mumshed.model.Student;
import com.seproject.mumshed.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class StudentController {


    @Autowired
    private StudentRepository studentrepository;

    @GetMapping("/addstudent")
    public String getAddStudentForm(Model model) {
        Student student=new Student();
        model.addAttribute("student", student);
        return "student/addstudentform";
    }

//
    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Student student) {
        studentrepository.save(student);
        return "redirect:/allstudents";
    }

    @GetMapping("/allstudents")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentrepository.findAll());
        return "student/showallstudent";
    }
    @GetMapping("/editstudent/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Student student = studentrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("student", student);
        return "student/studenteditform";
    }
    @PostMapping("/updatestudent/{id}")
    public String updateStudent(@PathVariable("id") String id, @Valid Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-user";
        }

        studentrepository.save(student);
        model.addAttribute("student", studentrepository.findAll());
        return "redirect:/allstudents";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        Student student = studentrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        studentrepository.delete(student);
        model.addAttribute("users", studentrepository.findAll());
        return "redirect:/allstudents";
    }


}
