package edu.mum.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{


}
