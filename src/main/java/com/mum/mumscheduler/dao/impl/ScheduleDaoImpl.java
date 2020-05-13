package com.mum.mumscheduler.dao.impl;

import com.mum.mumscheduler.dao.IScheduleDao;
import com.mum.mumscheduler.models.Schedule;
import com.mum.mumscheduler.respository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ScheduleDaoImpl implements IScheduleDao {

    @Autowired
    public ScheduleRepository repo;


    @Override
    public Schedule generateSchedule(String entryMonth) {
        return null;
    }

    @Override
    public Schedule getScheduleByEntryId(String id) {
        return null;
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return repo.findAll();
    }

    @Override
    public List<Schedule> getSchedulesOfUser(String userId) {
        return repo.findByUserId(userId);
    }
}
