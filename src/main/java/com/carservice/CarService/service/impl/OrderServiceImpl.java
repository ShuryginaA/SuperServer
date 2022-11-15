package com.carservice.CarService.service.impl;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void submit(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void submit(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
