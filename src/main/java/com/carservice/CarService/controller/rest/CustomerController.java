package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.User;
import com.carservice.CarService.data.dto.IdDto;
import com.carservice.CarService.data.dto.OrderDto;
import com.carservice.CarService.data.dto.UsernameDto;
import com.carservice.CarService.service.api.OrderService;
import com.carservice.CarService.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/getUserOrders")
    public List<OrderDto> getUserOrders(@RequestBody IdDto id) {
        List<OrderDto> list=orderService.getAllUserOrders(id.getId());
        return list;
    }

    @PostMapping("/getUserId")
    public Long getUserId(@RequestBody UsernameDto username) {
        if(userService.findUserByUsername(username.getUsername())==null)
            return -100L;
       return userService.findUserByUsername(username.getUsername()).getId();
    }
}
