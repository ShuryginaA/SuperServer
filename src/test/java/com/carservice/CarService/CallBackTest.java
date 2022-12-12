package com.carservice.CarService;

import com.carservice.CarService.data.CallBack;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.repositories.CallbackRepository;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.impl.AuthenticationServiceImpl;
import com.carservice.CarService.service.impl.CallbackServiceImpl;
import com.carservice.CarService.service.impl.UserServiceImpl;
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

import javax.swing.text.html.parser.Entity;
import java.util.Collections;

import static com.carservice.CarService.data.Role.RoleName.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CallBackTest {

    @Mock
    private CallbackRepository callbackRepository;

    @InjectMocks
    private CallbackServiceImpl callbackService;

    CallBack callBack = new CallBack();

    User managerUser = new User();

    @BeforeEach
    void setMockOutput() {
        MockitoAnnotations.openMocks(this);
        callBack.setId(Long.parseLong("1"));
        callBack.setName("test");
        callBack.setPhone("12345678");
        callBack.setComment("Comment");
    }

    @DisplayName("Callback_successful")
    @Test
    void Callback_successful() {
        assertEquals("Success", callbackService.save("{\"name\":\"test\"," +
                "\"phone\":\"0000000000\"," +
                "\"comment\":\"comment\"}"));
    }

    @DisplayName("Callback_failed")
    @Test
    void Callback_failed() {
        assertEquals("Data callback entered uncorrected", callbackService.save("{\"name\":\"\"," +
                "\"phone\":\"0000000000\"," +
                "\"comment\":\"comment\"}"));
    }


}