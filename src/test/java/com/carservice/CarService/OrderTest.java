package com.carservice.CarService;

import com.carservice.CarService.data.Order;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderTest {

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
		order = new Order("orderName", testUser, Order.Status.CREATED);
	}

	@DisplayName("Order_save_success")
	@Test
	void Order_save_success() {
		when(orderRepository.save(order)).thenReturn(order);
		try {
			assertEquals("Success", orderService.submit(order));
		} catch (AuthenticationException e) {
			Assertions.fail("Expected IOException");
		}
	}

	@DisplayName("Order_save_failed")
	@Test
	void Order_save_failed() {
		Order order_not_name = new Order("", testUser, Order.Status.CREATED);
		try {
			orderService.submit(order_not_name);
			Assertions.fail("Expected IOException");
		} catch (AuthenticationException thrown) {
			Assertions.assertEquals("Data oder entered uncorrected", thrown.getMessage());
		}
	}

	@DisplayName("Order_change_status_failed")
	@Test
	void Order_change_success() {
		assertEquals("Data status entered uncorrected", orderService.changeStatus(Long.parseLong("1"), ""));
	}

}
