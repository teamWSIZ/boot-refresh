package com.example.demo.controller;


import com.example.demo.model.Question;
import com.example.demo.model.Test;
import com.example.demo.service.QuestionRepo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions")
@Slf4j
public class QuestionController {
    @Autowired
    QuestionRepo questionRepo;

    @RequestMapping(value = "", method = GET)
    public Iterable<Question> getAllQuestions() {
        List<Question> all = Lists.newArrayList(questionRepo.findAll()); //guava
        Collections.sort(all, Comparator.comparingInt(Question::getQid));
        return all;
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

    @RequestMapping(method = PUT)
    public Question upsertQuestion(@RequestBody Question question) {
        boolean isUpdate = (question.getQid() != null);
        questionRepo.save(question);
        if (isUpdate) log.info("Question updated to [{}]", question);
        else log.info("New question created: [{}]", question);
        return question;
    }

}
