package edu.mum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Block;
import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {
	
	@Query("select s from Schedule s where s.entry.id = id")
	public Schedule findScheduleByEntryId(@Param("id") Long id);
	

}
