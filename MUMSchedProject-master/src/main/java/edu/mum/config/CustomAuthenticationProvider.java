package edu.mum.config;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.mum.service.CustomUserDetailsService;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
   
	
	@Autowired
	CustomUserDetailsService userdetailsService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails loginUser=userdetailsService.loadUserByUsername(username);
      
        logger.info("Name = " + username + " ,Password = " + password);
        
        // use the credentials and authenticate against the third-party system
        if(loginUser.getUsername().equals(username) &&  getPasswordEncoder().matches(loginUser.getPassword(), password)){
        		
        	logger.info("Succesful authentication!");
        	return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());	
        }
        
        logger.info("Login fail!");
        
        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}

			@Override
			public String encode(CharSequence charSequence) {
				System.out.println("Security");
				return charSequence.toString();
			}
		};
	}
	
}