package edu.mum.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Transcript;
import edu.mum.repository.TranscriptRepository;

@Service
public class TranscriptService {

	@Autowired
	TranscriptRepository transcriptRepository;
	
		public Transcript save(Transcript transcript) {
			return transcriptRepository.save(transcript);
		}

		public List<Transcript> getAllCourser() {
			return (List<Transcript>) transcriptRepository.findAll();
		}

		public Transcript getByStudentId(long stId) {
			return transcriptRepository.findByStudentId(stId);
		}
	}

