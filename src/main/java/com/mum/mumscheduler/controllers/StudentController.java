package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/addstudent")
    public String getAddStudentForm(Model model) {
        Student student=new Student();
        model.addAttribute("student", student);
        return "student/addstudentform";
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Student student) {
        studentService.add(student);
        return "redirect:/students/allstudents";
    }

    @GetMapping("/all")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student/showallstudent";
    }
    @GetMapping("/editstudent/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("student", student);
        return "student/studenteditform";
    }
    @PostMapping("/updatestudent/{id}")
    public String updateStudent(@PathVariable("id") String id, @Valid Student student,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "student/studenteditform";
        }
        studentService.update(student, id);
        model.addAttribute("student", studentService.getAllStudents());
        return "redirect:/students/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        studentService.delete(student.getId());
        model.addAttribute("users", studentService.getAllStudents());
        return "redirect:/students/all";
    }
}
