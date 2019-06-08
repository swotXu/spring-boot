package com.xuh.ticket12306.hellodb.controller;

import com.xuh.ticket12306.hellodb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hellodb")
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping("/selectid/{id}")
    public String selectCityById(@PathVariable int id){
        return cityService.selectCityById(id).toString();
    }
}
