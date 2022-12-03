package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.User;
import com.carservice.CarService.data.dto.ForSavingOrderDto;
import com.carservice.CarService.data.dto.OrderDto;
import com.carservice.CarService.data.dto.UpdateOrderDto;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.api.OrderService;
import com.carservice.CarService.service.api.UserService;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/allOrders")
    public List<OrderDto> getAllOrders() {
        List<Order> o=orderService.getAllOrders();

         return  o.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/save")
    public String save(@RequestBody ForSavingOrderDto dto) {
        Order orderEntity=new Order();
        orderEntity.setName(dto.getName());
        Optional<User> user=userRepository.findById(dto.getClientId());
        if(!user.isPresent()){
            return "Error:no such user";
        }
        orderEntity.setUser(user.get());
        orderEntity.setDate(dto.getDate());
        orderEntity.setStatus(Order.Status.CREATED);
        return orderService.submit(orderEntity);
    }

    @PostMapping("/update")
    public String update(@RequestBody UpdateOrderDto dto) {
        Optional<Order> order=orderRepository.findById(dto.getId());
        if(!order.isPresent()){
            return "Error:no such order";
        }
        order.get().setDate(dto.getNewDateAndTime());
        return orderService.submit(order.get());
    }

    @PostMapping("{clientId}/bookTime")
    public String bookTime(@PathVariable("clientId") Long clientId,
                           @RequestBody String time) {
        orderService.submit(new Order("Диагностика",
                userRepository.findById(clientId).get(),
                Order.Status.CREATED));
        return "Success";
    }

    @PostMapping("{orderId}/changeStatus")
    public String changeStatus(@PathVariable("orderId") Long orderId,
                           @RequestBody String newStatus) {
        return orderService.changeStatus(orderId,newStatus);
    }

}
