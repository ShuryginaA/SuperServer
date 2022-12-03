package com.carservice.CarService.service.api;

import com.carservice.CarService.data.CallBack;
import com.carservice.CarService.data.dto.CallBackDto;

import java.util.List;

public interface CallbackService {

    String save(String callBack);
    List<CallBack> getAll();
}
