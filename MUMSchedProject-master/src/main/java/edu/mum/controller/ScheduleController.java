package edu.mum.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Schedule;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.EntryService;
import edu.mum.service.ScheduleService;

@Controller
@RequestMapping("/admin")
public class ScheduleController {

	// inject via application.properties
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ScheduleRepository scheduleDao;
	@Autowired
	EntryService entryService;
	//ScheduleList
	@RequestMapping("/schedule/list")
	public String scheduleList(Map<String, Object> model) {

		model.put("schedule", scheduleDao.findAll());
		return "schedules";
	}
	//CreateForm
	@RequestMapping("/schedule/create")
	public String createSchedule(Model model) {

		model.addAttribute("entries", entryService.getAllEntry());

		return "generateSched";
	}
	//Create
	@RequestMapping(value = "/schedule/create", method = RequestMethod.POST)
	public String generateSchedule(@RequestParam("entry") String entry, Model model) {
		System.out.println("========>Generate schedule Controller Entry  "+entry);
	
		Schedule schedule = scheduleService.generateSched(entry);
		System.out.println("========>Generate schedule Controller Blocks  "+schedule.getEntry().getBlocks());
		if (schedule.getEntry().getBlocks().size()==0) {
			return "errorSchedule";
		}
		
		model.addAttribute("schedule", scheduleDao.findAll());
		return "schedules";
	}
	//EditForm
	@RequestMapping(value = "/schedule/edit/{id}", method = RequestMethod.GET)
	public String editSchedule(@PathVariable("id") Long id, Model model) {
		// Schedule schedule = scheduleService.generateSched(entry)
		model.addAttribute("schedule", scheduleDao.findById(id));
		return "editSched";
	}
	//Delete
	@RequestMapping(value = "/schedule/delete/{id}",  method = RequestMethod.GET)
	public String deleteSchedule(@PathVariable("id") Long id, Model model) {
		scheduleDao.deleteById(id);
		model.addAttribute("schedule", scheduleDao.findAll());
		return "schedules";
	}
	//ScheduleView
	@RequestMapping(value = "/schedule/view/{id}", method = RequestMethod.GET)
	public String viewSchedule(@PathVariable("id") Long id, Model model) {

		Schedule schedule = scheduleDao.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
		model.addAttribute("blocks", schedule.getEntry().getBlocks());
		model.addAttribute("entry",schedule.getEntry());
		return "viewSchedule";
	}
   //UPdate
	@RequestMapping(value = "/schedule/update", method = RequestMethod.POST)
	public String updateSchedule(@RequestParam("status") String status, @RequestParam("update") Long id, Model model) {
		System.out.println("===== update id "+id);
		Schedule schedule = scheduleDao.findById(id).orElseThrow(()-> new IllegalArgumentException("Illegal argument " + id));
		schedule.setStatus(status);
		scheduleDao.save(schedule);
		model.addAttribute("schedule", scheduleDao.findAll());
		return "schedules";
	}

}
