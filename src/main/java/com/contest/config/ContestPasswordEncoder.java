package com.contest.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ContestPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(encodedPassword != null && encodedPassword.length() !=0) {
			
			return	encoder.matches(rawPassword, encodedPassword);

		}
		
		return false;
	}

}
