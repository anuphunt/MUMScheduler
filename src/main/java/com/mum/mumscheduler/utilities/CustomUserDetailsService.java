package com.mum.mumscheduler.utilities;

import com.mum.mumscheduler.models.CustomUserDetails;
import com.mum.mumscheduler.models.User;
import com.mum.mumscheduler.respository.StudentRepository;
import com.mum.mumscheduler.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

     @Autowired
     UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepo.findUserByUsername(username);
        
        user.orElseThrow(()->new UsernameNotFoundException("Not found: "+ username));

        return user.map(CustomUserDetails::new).get();
    }
}
