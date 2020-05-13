package com.mum.mumscheduler.dao;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Optional;

public interface IEntryDao {
    Optional<Entry> getEntryByIntake(String intake);
    List<Entry> getAllEntry();
    Entry add(Entry entry);
    Entry update(Entry entry, String intake);
    void delete(String intake);
}
