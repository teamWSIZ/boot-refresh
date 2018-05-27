package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static java.lang.Thread.sleep;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@Slf4j
public class AppController {

    @RequestMapping(value = "/status")
    public String showStatus() {
        return "Aplikacja działa prawidłowo";
    }


    @RequestMapping(value = "/current_time", method = GET)
    public String currentTime() {
        return (new Date()).toString();
    }


    @RequestMapping(value = "/longcall", method = GET)
    public NumberResponse currentTime(@RequestParam(value = "duration") Integer duration) throws Exception {
        sleep(duration);
        return new NumberResponse("OK", duration);
    }




}
