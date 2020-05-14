package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.ICourseService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
    @RequestMapping(value = "courses")
    public class CourseController {

        List<String> prerequisites;

        @Autowired
        private ICourseService courseService;

        @GetMapping("/addcourse")
        public String getAddCourseForm(Model model) {
            Course course=new Course();

            prerequisites = Arrays.asList("WAP", "DBMS", "SE");
            model.addAttribute("prerequisites", prerequisites);
            model.addAttribute("course", course);
            return "course/addcourseform";
        }

        //Get Course by course Name
        public Course getCourseByCourseName(){
            //implement this
            return new Course();
        }


        @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
        public String addCourse(@ModelAttribute("course") Course course) {
            courseService.add(course);
            return "redirect:/courses/all";
        }

        @GetMapping("/all")
        public String showAllCourses(Model model) {
            model.addAttribute("courses", courseService.getAllCourses());
            return "course/showallcourse";
        }
        @GetMapping("/editcourse/{id}")
        public String showUpdateForm(@PathVariable("id") String id, Model model) {
            Course course = courseService.getCourseById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

            model.addAttribute("course", course);
            return "course/courseeditform";
        }
        @PostMapping("/updatecourse/{id}")
        public String updateCourse(@PathVariable("id") String id, @Valid Course course,
                                    BindingResult result, Model model) {
            System.out.println(course.getCourseName());

            if (result.hasErrors()) {
                course.setCourseId(id);
                return "course/courseeditform";
            }
            courseService.update(course, id);
            model.addAttribute("course", courseService.getAllCourses());
            return "redirect:/courses/all";
        }

        @GetMapping("/delete/{id}")
        public String deleteUser(@PathVariable("id") String id, Model model) {
            Course course = courseService.getCourseById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            courseService.delete(course.getCourseId());
            model.addAttribute("users", courseService.getAllCourses());
            return "redirect:/courses/all";
        }
    }


