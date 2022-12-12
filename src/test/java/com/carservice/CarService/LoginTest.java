package com.carservice.CarService;

import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.impl.AuthenticationServiceImpl;
import com.carservice.CarService.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

// import org.junit.Assert;

import java.util.Collections;

import static com.carservice.CarService.data.Role.RoleName.CUSTOMER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @InjectMocks
    private UserServiceImpl userService;

    User testUser = new User();

    User managerUser = new User();

    @BeforeEach
    void setMockOutput() {
        MockitoAnnotations.openMocks(this);
        testUser = new User("test",
                "test",
                "testFullName",
                "1234567890",
                "test@mail.ru"
        );
    }
    @DisplayName("Login_without_username")
    @Test
    void Login_without_username() {
        try {
            authenticationService.autoLogin("", "test");
            Assertions.fail("Expected IOException");
        } catch (AuthenticationException thrown) {
            Assertions.assertEquals("Data user entered uncorrected", thrown.getMessage());
        }
    }

    @DisplayName("Login_without_password")
    @Test
    void Login_without_password() {
        try {
            authenticationService.autoLogin("test", "");
            Assertions.fail("Expected IOException");
        } catch (AuthenticationException thrown) {
            Assertions.assertEquals("Data user entered uncorrected", thrown.getMessage());
        }
    }
}


