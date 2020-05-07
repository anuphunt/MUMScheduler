package com.mum.mumscheduler.respository;

import com.mum.mumscheduler.models.Block;
import com.mum.mumscheduler.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends MongoRepository<Block,String> {

}
