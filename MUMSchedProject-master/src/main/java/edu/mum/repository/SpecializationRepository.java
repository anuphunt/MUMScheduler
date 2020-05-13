package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Specialization;
@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long>{

}
