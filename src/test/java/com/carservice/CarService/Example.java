package com.carservice.CarService;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.User;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class Example {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    Order order=new Order();

    @BeforeEach
    void setMockOutput() {
        User testUser = new User("test",
                "test",
                "testFullName",
                null,
                "test@mail.ru"
        );
        order=new Order("orderName",testUser, Order.Status.CREATED);

    }

    @DisplayName("Example")
    @Test
    void submitOrderTest() {
        // иммитируем возвращаемое значение от репозитория
        // поскольку мы тестируем изолированно от БД чисто метод
        // соответственно при попытке подключения будет ошибка
        when(orderRepository.save(order)).thenReturn(order);
        assertEquals("Success",orderService.submit(order));
    }

}


