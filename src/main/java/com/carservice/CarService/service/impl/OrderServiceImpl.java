package com.carservice.CarService.service.impl;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String submit(Order order) {
        orderRepository.save(order);
        return "Success";
    }

    @Override
    public void submit(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    @Override
    public String changeStatus(Long id, String newStatus) {
        Optional<Order> oldOrder =orderRepository.findById(id);
        if(oldOrder.isPresent()){
            Order tmpOrder=oldOrder.get();
            tmpOrder.setStatus(Order.Status.valueOf(newStatus));
            orderRepository.save(tmpOrder);
            return "Success";
        }
        return "Error occurred";
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
