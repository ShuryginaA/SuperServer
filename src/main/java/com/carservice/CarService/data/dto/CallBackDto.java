package com.carservice.CarService.data.dto;

import com.carservice.CarService.data.CallBack;
import com.carservice.CarService.data.Order;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CallBackDto {
    private String name;
    private String phone;
    private String comment;

    public CallBackDto(CallBack o) {
        this.name = o.getName();
        this.phone = o.getPhone();
        this.comment = o.getComment();
    }
}
