package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.services.IBlockService;
import com.mum.mumscheduler.services.IEntryService;
import com.mum.mumscheduler.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "entries")
public class EntryController {

    @Autowired
    public IEntryService entryService;

    @GetMapping("/addentry")
    public String getAddEntryForm(Model model) {
        Entry entry=new Entry();

        model.addAttribute("entry",entry);
        return "entry/addentryform";
    }

    @RequestMapping(value = "/addentry", method = RequestMethod.POST)
    public String addEntry(@ModelAttribute("entry") Entry entry) {

        entryService.add(entry);
        return "redirect:/entries/all";
    }

    @GetMapping("/all")
    public String showAllEntry(Model model) {
        model.addAttribute("entries", entryService.getAllEntry());
        return "entry/showallentry";
    }
    @GetMapping("/editentry/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Entry entry = entryService.getEntryByIntake(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("entry", entry);
        return "entry/entryeditform";
    }

    @PostMapping("/updateentry/{id}")
    public String updateEntry(@PathVariable("id") String id, @Valid Entry entry,
                               BindingResult result, Model model) {
        System.out.println(entry.getIntake());

        if (result.hasErrors()) {
            entry.setIntake(id);
            return "entry/entryeditform";
        }
        entryService.update(entry, id);
        model.addAttribute("entry", entryService.getAllEntry());
        return "redirect:/entries/all";
    }
        @GetMapping("/delete/{id}")
        public String deleteUser(@PathVariable("id") String id, Model model) {
            Entry entry = entryService.getEntryByIntake(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            entryService.delete(entry.getIntake());
            model.addAttribute("users", entryService.getAllEntry());
            return "redirect:/entries/all";
        }

}
