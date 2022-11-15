package com.carservice.CarService.service.impl;

import com.carservice.CarService.service.api.AuthenticationService;
import com.carservice.CarService.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    public Collection<? extends GrantedAuthority> getLoggedUserRoles() {
       return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        logger.debug("auth obj: ", authentication);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    @Override
    public void updateAuthoritiesInSecurityContext(Authentication authentication) {
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }
}
