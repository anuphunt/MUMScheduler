package com.mum.mumscheduler.services.impl;

import com.mum.mumscheduler.dao.IFacultyDao;
import com.mum.mumscheduler.dao.IScheduleDao;
import com.mum.mumscheduler.dao.IStudentDao;
import com.mum.mumscheduler.models.Schedule;
import com.mum.mumscheduler.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    public IScheduleDao scheduleDao;

    @Override
    public Schedule generateSchedule(String entry) {
        return null;
    }

    @Override
    public Schedule getScheduleByEntryId(String id) {
        return null;
    }

    @Override
    public List<Schedule> getAllSchedule() {
      return scheduleDao.getAllSchedule();
    }

    @Override
    public List<Schedule> getSchedulesOfUser(String userId) {
        return  scheduleDao.getSchedulesOfUser(userId)  ;
    }
}
