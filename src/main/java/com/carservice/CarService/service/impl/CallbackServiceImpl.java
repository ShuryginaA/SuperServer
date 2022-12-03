package com.carservice.CarService.service.impl;

import com.carservice.CarService.data.CallBack;
import com.carservice.CarService.data.dto.CallBackDto;
import com.carservice.CarService.repositories.CallbackRepository;
import com.carservice.CarService.repositories.OrderRepository;
import com.carservice.CarService.service.api.CallbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private CallbackRepository callBackRepository;

    @Override
    public String save(String callBack) {
        var objectMapper = new ObjectMapper();
        CallBackDto callbackDto;
        try {
            callbackDto = objectMapper.readValue(callBack,CallBackDto.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return "Error occurred";
        }
        CallBack entity=new CallBack();
        entity.setName(callbackDto.getName());
        entity.setPhone( callbackDto.getPhone());
        entity.setComment( callbackDto.getComment());
        callBackRepository.save(entity);
        return "Success";
    }

    @Override
    public List<CallBack> getAll() {
        return callBackRepository.findAll();
    }
}
