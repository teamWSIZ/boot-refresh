package com.example.demo.controller;


import com.example.demo.model.Test;
import com.example.demo.service.QuestionRepo;
import com.example.demo.service.TestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/exam")
@Slf4j
public class AccountController {

    @Autowired
    TestRepo testRepo;
    @Autowired
    QuestionRepo questionRepo;

    @RequestMapping(value = "/status")
    public String showStatus() {
        return "App running OK";
    }


    @RequestMapping(value = "/", method = GET)
    public Iterable<Test> getAllTests() {
        return testRepo.findAll();
    }

    @RequestMapping(value = "/{tid}", method = GET)
    public Test getTestById(@PathVariable Integer aid) {
        return testRepo.findOne(aid);
    }

    @RequestMapping(value = "/", method = PUT)
    public Test updateOrCreateTest(@RequestBody Test test) {
        if (test.getTid()==null) {
            log.info("Creating test {}", test);
        } else {
            log.info("Updating test {}", test);
        }
        return testRepo.save(test);
    }

    @RequestMapping(value = "/{tid}", method = DELETE)
    public void deleteTest(@PathVariable Integer aid) {
        log.info("Removing account {}", aid);
        testRepo.delete(aid);
    }


}
