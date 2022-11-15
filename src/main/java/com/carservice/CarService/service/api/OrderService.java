package com.carservice.CarService.service.api;

import com.carservice.CarService.data.Order;

import java.util.List;

public interface OrderService {

    void submit(Order order);

    void submit(List<Order> orders);

    List<Order> getAllOrders();
}
