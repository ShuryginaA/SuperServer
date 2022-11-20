package com.carservice.CarService.service.api;

import com.carservice.CarService.data.Order;

import javax.ws.rs.core.Response;
import java.util.List;

public interface OrderService {

    String submit(Order order);

    void submit(List<Order> orders);

    List<Order> getAllOrders();
}
