package com.carservice.CarService.data.dto;

import com.carservice.CarService.data.Order;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private String name;
    private String clientName;
    private Order.Status status;

    public OrderDto(Order o) {
        this.id = o.getId();
        this.name = o.getName();
        this.clientName = o.getUser().getFullName();
        this.status = o.getStatus();
    }
}
