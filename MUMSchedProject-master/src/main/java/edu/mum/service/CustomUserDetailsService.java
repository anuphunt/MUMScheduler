package edu.mum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.mum.domain.CustomUserPrincipal;
import edu.mum.domain.UserProfile;
import edu.mum.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
       Optional<UserProfile> optionalUser= userRepository.getUserByUserName(username);
       optionalUser
                .orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
       return optionalUser
              .map(CustomUserPrincipal::new).get();
                 
	   
    }
   
   
}