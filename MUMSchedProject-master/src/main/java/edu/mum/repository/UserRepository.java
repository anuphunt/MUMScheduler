package edu.mum.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.domain.UserProfile;

@Repository
public interface UserRepository extends CrudRepository<UserProfile, Long> {
  
   public Optional<UserProfile> getUserByUserName(@Param("username")String username);
  
  @Query("select u from UserProfile u where u.userStatus=:status")
  public List<UserProfile> getActiveUser(@Param("status")String status);
  
  
}
