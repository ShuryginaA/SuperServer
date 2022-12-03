package com.carservice.CarService.data.dto;

import com.carservice.CarService.data.Order;
import lombok.Data;
@Data
public class ForSavingOrderDto  {
    private String name;
    private Long clientId;
    private String date;
}