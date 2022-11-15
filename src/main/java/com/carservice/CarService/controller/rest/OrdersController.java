package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.dto.OrderDto;
import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/orders")
@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrders")
    public List<OrderDto> getAllItems() {
        return orderService.getAllOrders().stream()
                .map(OrderDto::new).collect(Collectors.toList());
    }
    @GetMapping("/allOrdersTest")
    public String getAllItemsTest() {
        return "Here will be list of orders";
    }
    @GetMapping("/getUserOrders")
    public String getUserOrders() {
        return "Here will be concrete user orders";
    }
}
