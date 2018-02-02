package com.example.demo.controller;


import com.example.demo.model.Shipper;
import com.example.demo.model.User;
import com.example.demo.service.ShipperRepo;
import com.example.demo.service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ShipperRepo shipperRepo;

    @RequestMapping(value = "/status")
    public String showStatus() {
        return "App running OK";
    }

    //// USERS
    @RequestMapping(value = "/users", method = GET)
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    //// SHIPPERS
    @RequestMapping(value = "/shippers", method = GET)
    public Iterable<Shipper> getShippers() {
        return shipperRepo.findAll();
    }


}
