package com.carservice.CarService;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.repositories.CallbackRepository;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.repositories.RoleRepository;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.impl.AuthenticationServiceImpl;
import com.carservice.CarService.service.impl.CallbackServiceImpl;
import com.carservice.CarService.service.impl.OrderServiceImpl;
import com.carservice.CarService.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

// import org.junit.Assert;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RegistrationTest {

    @Mock
    private CallbackRepository callbackRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @InjectMocks
    private CallbackServiceImpl callbackService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @InjectMocks
    private UserServiceImpl userService;

    Order order = new Order();
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

    @DisplayName("Registration_successful")
    @Test
    void Registration_successful() {
        when(userRepository.save(testUser)).thenReturn(testUser);
        try {
            testUser.setRoles(Collections.singletonList(userService.findRoleByName(Role.RoleName.CUSTOMER)));
            userService.saveUser(testUser);
        } catch (AuthenticationException thrown) {
            Assertions.fail("Expected IOException");
        }
    }

    @DisplayName("Registration_without_login")
    @Test
    void Registration_without_login() {
        testUser = new User("",
                "test",
                "testFullName",
                "1234567890",
                "test@mail.ru"
        );
        try {
            testUser.setRoles(Collections.singletonList(userService.findRoleByName(Role.RoleName.CUSTOMER)));
            userService.saveUser(testUser);
            Assertions.fail("Expected IOException");
        } catch (AuthenticationException thrown) {
            Assertions.assertEquals("Data user entered uncorrected", thrown.getMessage());
        }
    }

    @DisplayName("Registration_without_password")
    @Test
    void Registration_without_password() {
        testUser = new User("test",
                "",
                "testFullName",
                "1234567890",
                "test@mail.ru"
        );
        try {
            testUser.setRoles(Collections.singletonList(userService.findRoleByName(Role.RoleName.CUSTOMER)));
            userService.saveUser(testUser);
            Assertions.fail("Expected IOException");
        } catch (AuthenticationException thrown) {
            Assertions.assertEquals("Data user entered uncorrected", thrown.getMessage());
        }
    }
}


