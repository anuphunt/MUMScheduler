package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Schedule;

import java.util.List;

public interface IScheduleDao {
    Schedule generateSchedule(String entryMonth);
    Schedule getScheduleByEntryId(String id);
    List<Schedule> getAllSchedule();
    List<Schedule> getSchedulesOfUser(String userId);
}
