package com.devsuperior.demo.controllers;

import com.devsuperior.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/citys")
public class CityController {

    @Autowired
    private CityService cityService;



}
