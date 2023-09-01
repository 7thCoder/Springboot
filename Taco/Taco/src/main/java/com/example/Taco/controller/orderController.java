package com.example.Taco.controller;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.Taco.taco.tacOrder;
import com.example.Taco.Repository.*;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacOrder")
public class orderController {

    private orderRepository orderRepo;

    @Autowired
    public orderController(orderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid tacOrder order,Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: {}",order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        
        return "redirect:/";
    }
}
