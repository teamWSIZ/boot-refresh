package com.example.demo.controller;


import com.example.demo.model.Crypto;
import com.example.demo.model.User;
import com.example.demo.service.CryptoRepo;
import com.example.demo.service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CryptoRepo cryptoRepo;


    @RequestMapping(value = "/status")
    public String showStatus() {
        return "App running OK";
    }

    //// USERS
    @RequestMapping(value = "/show_users", method = GET)
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    @RequestMapping(value = "/create_user", method = GET)
    public User createUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "nickname") String nickname) {
        User nowy = new User(null, name, nickname, true);
        return userRepo.save(nowy);
    }


    @RequestMapping(value = "/users")
    public User findUserById(@RequestParam(value = "uid") Integer uid) {
        return userRepo.findOne(uid);
    }

    //// CRYPTO
    @RequestMapping(value = "/passwords")
    public Iterable<Crypto> getAllPasswords() {
        return cryptoRepo.findAll();
    }




}
