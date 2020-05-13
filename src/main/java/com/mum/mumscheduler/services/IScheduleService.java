package com.mum.mumscheduler.services;

import com.mum.mumscheduler.models.Schedule;

import java.util.List;

public interface IScheduleService {

    public Schedule generateSchedule(String entry);
    public Schedule getScheduleByEntryId(String id);
    public List<Schedule> getAllSchedule();
    public List<Schedule> getSchedulesOfUser(String userId);
}
