package com.carservice.CarService.config.security;

import com.carservice.CarService.viewModel.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService  {

	private final static Logger logger = LoggerFactory.getLogger(SecurityService.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	public Authentication authenticate(LoginForm authData) {

		UsernamePasswordAuthenticationToken authRequest =
				new UsernamePasswordAuthenticationToken(authData.getUsername(), authData.getPassword());
		Authentication result = authenticationManager.authenticate(authRequest);
		
		if(result == null) {
			return null;
		}
		
		SecurityContextHolder.getContext().setAuthentication(result);
		
		return result;
		
	}

}
