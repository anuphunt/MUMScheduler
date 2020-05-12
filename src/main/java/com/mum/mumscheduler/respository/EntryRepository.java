package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Course;
import com.mum.mumscheduler.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends MongoRepository<Entry,String> {
    //public Course findEntryByIntake(String intake);
}