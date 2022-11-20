package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.dto.OrderDto;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.api.OrderService;
import com.carservice.CarService.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/orders")
@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/allOrders")
    public List<OrderDto> getAllItems() {
        return orderService.getAllOrders().stream()
                .map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("{clientId}/bookTime")
    public String bookTime(@PathVariable("clientId") Long clientId,
                           @RequestBody String time) {
        LocalTime t = LocalTime.parse(time) ;
        orderService.submit(new Order("Диагностика",
                userRepository.findById(clientId).get(),
                Order.Status.CREATED));
        return "Success";
    }

    @GetMapping("/getUserOrders")
    public String getUserOrders() {
        return "Here will be concrete user orders";
    }



}
