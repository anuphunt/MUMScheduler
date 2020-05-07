package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
    public Admin findStudentByName(String name);
}
