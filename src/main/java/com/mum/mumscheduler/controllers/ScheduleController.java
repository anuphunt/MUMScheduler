package com.mum.mumscheduler.controllers;

import com.mum.mumscheduler.models.Schedule;
import com.mum.mumscheduler.services.IEntryService;
import com.mum.mumscheduler.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @Autowired
    IEntryService entryService;

    @GetMapping("/schedule/all")
    public String getAllSchedule(Model model){
        List<Schedule> schedules = scheduleService.getAllSchedule();
        model.addAttribute("schedules", schedules);
        return "schedules/allSchedules";
    }

    @GetMapping("/schedule/{id}")
    public String getScheduleByUserId(Model model){
        List<Schedule> schedules = scheduleService.getAllSchedule();
        model.addAttribute("schedules", schedules);
        return "schedules/userSchedule";
    }

    @GetMapping("/schedule/generate/{entry}")
    public String generateSchedule(Model model, @PathVariable String entry){
        scheduleService.generateSchedule(entry);
        return "test";
    }
}
