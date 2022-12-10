package com.carservice.CarService.service.api;

import com.carservice.CarService.exceptions.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AuthenticationService {

    boolean isAuthenticated();

    Collection<? extends GrantedAuthority> getLoggedUserRoles();

    void autoLogin(String username, String password) throws AuthenticationException;

    void updateAuthoritiesInSecurityContext(Authentication authentication);

    public String getRoleFromAuth(Authentication authentication);
}
