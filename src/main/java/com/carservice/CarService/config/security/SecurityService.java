/*
 
 Copyright 2016 James Cox <james.s.cox@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

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
