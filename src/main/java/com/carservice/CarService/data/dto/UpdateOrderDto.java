package com.carservice.CarService.data.dto;

import lombok.Data;

@Data
public class UpdateOrderDto {
    private Long id;
    private String newDateAndTime;
}