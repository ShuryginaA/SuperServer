package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.dto.OrderDto;
import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getUserOrders")
    public String getUserOrders() {
        return "Here will be concrete user orders";
    }
}
