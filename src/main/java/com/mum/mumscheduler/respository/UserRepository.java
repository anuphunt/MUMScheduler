package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Student;
import com.mum.mumscheduler.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findUserByUsername(String name);
}
