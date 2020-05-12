package com.mum.mumscheduler.services;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Entry;
import com.mum.mumscheduler.models.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEntryService {

    Optional<Entry> getEntryByIntake(String intake);
    List<Entry> getAllEntry();
    void delete(String intake);
    Entry add(Entry intake);
    Entry update(Entry entry, String intake);
}
