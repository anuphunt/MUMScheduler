package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Faculty;
import com.mum.mumscheduler.services.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "faculty")
public class FacultyController {

    @Autowired
    public IFacultyService facultyService;

    //Get add form
    @GetMapping("/addfaculty")
    public String getAddFacultyForm(Model model){
        Faculty faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        return "faculty/addfacultyform";
    }

    //Add faculty
    @RequestMapping(value="/addfaculty", method= RequestMethod.POST)
    public String addFaculty(@ModelAttribute("faculty") Faculty faculty){
        facultyService.add(faculty);
        return "redirect:/faculty/all";
    }

    @GetMapping("/all")
    public String showAllFaculty(Model model){
        model.addAttribute("faculties", facultyService.getAllFaculty());
        return "faculty/showallfaculty";
    }

    //Get update form
    @GetMapping("/editfaculty/{id}")
    public String showEditFacultyForm(@PathVariable("id") String id, Model model){
        Faculty faculty = facultyService.getFacultyById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Faculty Id:" + id));

        model.addAttribute("faculties", faculty);
        return "faculty/editfacultyform";
    }

    @PostMapping("/updatefaculty/{id}")
    public String updateFaculty(@PathVariable("id") String id, @Valid Faculty faculty,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            faculty.setFacultyId(id);
            return "faculty/editfacultyform";
        }
        facultyService.update(faculty, id);
        model.addAttribute("faculty", facultyService.getAllFaculty());
        return "redirect:/faculty/all";
    }

    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam("id") String id){
        facultyService.delete(id);
        return "redirect:/faculty/all";
    }




}
