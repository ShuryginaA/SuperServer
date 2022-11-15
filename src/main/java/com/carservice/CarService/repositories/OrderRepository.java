package com.carservice.CarService.repositories;


import com.carservice.CarService.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
