package edu.mum;

import edu.mum.domain.Role;
import edu.mum.domain.UserProfile;
import edu.mum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class MumSchedProjectApplication {

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(MumSchedProjectApplication.class, args);
	}
}
