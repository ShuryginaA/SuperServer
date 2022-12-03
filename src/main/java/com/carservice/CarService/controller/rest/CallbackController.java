package com.carservice.CarService.controller.rest;

import com.carservice.CarService.data.CallBack;
import com.carservice.CarService.data.Order;
import com.carservice.CarService.data.dto.CallBackDto;
import com.carservice.CarService.repositories.CallbackRepository;
import com.carservice.CarService.service.api.CallbackService;
import com.carservice.CarService.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/callBack")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @PostMapping("/orderCallback")
    public String bookTime(@RequestBody String callbackDto) {
        callbackService.save(callbackDto);
        return "Success";
    }

    @GetMapping("/allCallbacks")
    public List<CallBack> getAll() {
        return callbackService.getAll();
    }
}
