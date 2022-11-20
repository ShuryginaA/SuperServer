package com.carservice.CarService.controller.rest;

import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getUserOrders")
    public String getUserOrders() {
        return "Here will be concrete user orders";
    }

    @GetMapping("/getFreeTime")
    public String getFreeTime() {
        return "Here will be concrete user orders";
    }
}
