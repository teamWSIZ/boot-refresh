package com.example.demo.controller;


import com.example.demo.model.Question;
import com.example.demo.service.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions")
@Slf4j
public class QuestionController {
    @Autowired
    QuestionRepo questionRepo;


    @RequestMapping(value = "", method = GET)
    public Iterable<Question> getAllQuestions() {
        return questionRepo.findAll();
    }


    @RequestMapping(value = "new", method = GET)
    public Question newQuesiton(
            @RequestParam(name = "text") String text,
            @RequestParam(name = "q1") String q1,
            @RequestParam(name = "q2") String q2,
            @RequestParam(name = "q3") String q3,
            @RequestParam(name = "correct") Integer correct
    ) {
        Question nowe = new Question(null, text, q1, q2, q3, correct, 1, true);

        return questionRepo.save(nowe);
    }

}
