package com.mum.mumscheduler.controllers;


import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Section;
import com.mum.mumscheduler.services.ISectionService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="section")
public class SectionController {

    @Autowired
    public ISectionService sectionService;

    @Autowired
    public CourseController courseController;

    @Autowired
    public IStudentService studentServiceForList;

    @GetMapping("/addsection")
    public String getAddSectionForm(Model model){
        Section section = new Section();
        model.addAttribute("section", section);
        return "section/addsectionform";
    }

    @RequestMapping(value = "/addsection", method = RequestMethod.POST)
    public String addSection(@ModelAttribute("section") Section section){
        Course course = courseController.getCourseByCourseName();
        //Section section1 = new Section();
        sectionService.add(section);
        return "redirect:section/all";
    }

    @GetMapping("/all")
    public String showAllSection(Model model){
        model.addAttribute("showSectionForm", sectionService.getAllSection());
        return "section/showallsection";

    }

    @GetMapping("/editsection/{id}")
    public String showEditSectionForm(@PathVariable("id") String id, Model model){
        Section section = sectionService.getSectionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Section Id:" + id));

        model.addAttribute("sectionsEdit", section);
        return "section/editsectionform";
    }

    @PostMapping("/updatesection/{id}")
    public String updateSection(@PathVariable("id") String id, @Valid Section section,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            section.setId(id);
            return "section/editsectionform";
        }
        sectionService.update(section, id);
        model.addAttribute("section", sectionService.getAllSection());
        return "redirect:/section/all";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id){
        sectionService.delete(id);
        return "redirect:section/all";
    }

}
