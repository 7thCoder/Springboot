package com.contentcal.contentcal.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contentcal.contentcal.config.contentCalendarconfig;

@RestController
public class homeController {

    private final contentCalendarconfig properties;

    public homeController(contentCalendarconfig properties){
        this.properties = properties;
    }
   
    @GetMapping("/")
    public contentCalendarconfig home(){
        return properties;
    }
}
