package com.mum.mumscheduler.respository;


import com.mum.mumscheduler.models.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends MongoRepository<Section,String> {

}
