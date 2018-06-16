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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions")
@Slf4j
public class QuestionController {
    @Autowired
    QuestionRepo questionRepo;

    private void bla() {
        Question q = new Question();
        q.setAnswer1("Abra kadabra");
    }


    /**
     * Ta operacja bedzie dostepna pod
     *
     * powinna zwrocic (json) tabele z pelnymi obiektami typu
     * Question
     */
    @RequestMapping(value = "", method = GET)
    public Iterable<Question> getAllQuestions() {
        //nieposotrowane można dostać przez:
        //questionRepo.findAll()

        return questionRepo.findAllByOrderByQidAsc();
    }

    //ściąganie wszystkich pytań, których typ ma w opisie 'text'
    //http://localhost:8888/questions/typecontains?text=logy
    @RequestMapping(value = "/typecontains", method = GET)
    public Iterable<Question> getAllQuestions(@RequestParam(value = "text") String text) {
        String wildcard = "%" + text + "%";
        return questionRepo.findAllInterestingQuestionsWithTypeSimilarTo(wildcard);
    }

    @RequestMapping(method = PUT)
    public Question upsertQuestion(@RequestBody Question question) {
        boolean isUpdate = (question.getQid() != null);
        questionRepo.save(question);
        if (isUpdate) log.info("Question updated to [{}]", question);
        else log.info("New question created: [{}]", question);
        return question;
    }

    @RequestMapping(value = "/{qid}", method = DELETE)
    public GenericResponse deleteQuestion(@PathVariable Integer qid) {
        log.info("Deleting question with qid=[{}]", qid);
        questionRepo.delete(qid);
        return new GenericResponse("OK", "OK");
    }

    ////// próby metod JPA

    //http://localhost:8888/questions/bytype?typeid=22
    @RequestMapping(value = "/bytype", method = GET)
    public Iterable<Question> getQuestionsByType(@RequestParam(value = "typeid") Integer typeid) {
        return questionRepo.findByTypeid(typeid);
    }

    //http://localhost:8888/questions/bytext?textprefix=Poj
    @RequestMapping(value = "/bytext", method = GET)
    public Iterable<Question> getQuestionsByText(@RequestParam(value = "textprefix") String textprefix) {
        return questionRepo.findByTextStartingWith(textprefix);
    }

    //http://localhost:8888/questions/deltype?typeid=1
    @RequestMapping(value = "/deltype", method = GET)
    public GenericResponse deleteQuestionsByType(@RequestParam(value = "typeid") Integer typeid) {
        questionRepo.deleteByTypeid(typeid);
        return new GenericResponse();
    }


}
