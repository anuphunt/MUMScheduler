package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;


import edu.mum.domain.Transcript;

public interface TranscriptRepository extends CrudRepository<Transcript, Long>   {
		public Transcript findByStudentId(long stId);
}
