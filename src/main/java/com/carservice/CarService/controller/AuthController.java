package com.carservice.CarService.controller;

import com.carservice.CarService.config.security.SecurityService;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.service.api.AuthenticationService;
import com.carservice.CarService.service.api.UserService;
import com.carservice.CarService.validator.UserValidator;
import com.carservice.CarService.viewModel.LoginForm;
import com.carservice.CarService.viewModel.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.carservice.CarService.data.Role.RoleName.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        return "login";
    }
    @PostMapping("/auth")
    public Authentication authenticate (String authData) {

        return securityService.authenticate(authData);
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
      return "OK";
    }

    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute("userDTO") RegistrationForm form, BindingResult bindingResult)
            throws AuthenticationException {
        userValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) return "registration";
        User user = form.toUser();
        user.setRoles(Collections.singletonList(userService.findRoleByName(Role.RoleName.CUSTOMER)));
        String unhashedPassord = user.getPassword();
        userService.saveUser(user);
        authenticationService.autoLogin(user.getUsername(), unhashedPassord);
//        return "redirect:/userPage";
        return "userPage";

    }
    @GetMapping("/logout")
    public String logout() {
        if (authenticationService.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return null;
    }

}
