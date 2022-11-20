package com.carservice.CarService.controller;

import com.carservice.CarService.config.security.SecurityService;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.data.dto.AuthDto;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.service.api.AuthenticationService;
import com.carservice.CarService.service.api.UserService;
import com.carservice.CarService.validator.UserValidator;
import com.carservice.CarService.viewModel.LoginForm;
import com.carservice.CarService.viewModel.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


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
    public AuthDto authenticate (@RequestBody LoginForm authData) {
        if (authenticationService.isAuthenticated()){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            new AuthDto(authenticationService.getRoleFromAuth(
                    authentication),
                    userService.findUserByUsername(authData.getUsername()).getId());
        }
        return new AuthDto(authenticationService.getRoleFromAuth(
                securityService.authenticate(authData)),
                userService.findUserByUsername(authData.getUsername()).getId());
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
      return "OK";
    }

    @PostMapping("/registration")
    public String processRegistration(@RequestBody RegistrationForm form)
            throws AuthenticationException {
//        userValidator.validate(form, bindingResult);
//        if (bindingResult.hasErrors()) return "registration";
        User user = form.toUser();
        user.setRoles(Collections.singletonList(userService.findRoleByName(Role.RoleName.CUSTOMER)));
        String unhashedPassord = user.getPassword();
        userService.saveUser(user);
        authenticationService.autoLogin(user.getUsername(), unhashedPassord);
        return "Success";

    }
    @GetMapping("/logout")
    public String logout() {
        if (authenticationService.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return null;
    }

}
