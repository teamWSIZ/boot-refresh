package com.example.demo.controller;


import com.example.demo.model.QType;
import com.example.demo.model.Question;
import com.example.demo.service.QuestionRepo;
import com.example.demo.service.QuestionTypeRepo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/qtypes")
@Slf4j
public class TypeController {
    @Autowired
    QuestionTypeRepo typeRepo;

    /**
     * Ta operacja bedzie dostepna pod
     *
     * powinna zwrocic (json) tabele z pelnymi obiektami typu
     * Question
     */
    @RequestMapping(value = "", method = GET)
    public Iterable<QType> getAllQuestions() {
        List<QType> all = Lists.newArrayList(typeRepo.findAll()); //guava
        Collections.sort(all, Comparator.comparingInt(QType::getQtid));
        return all;
    }

    @RequestMapping(method = PUT)
    public QType upsertQuestion(@RequestBody QType qType) {
        boolean isUpdate = (qType.getQtid() != null);
        typeRepo.save(qType);
        if (isUpdate) log.info("QType updated to [{}]", qType);
        else log.info("New qtype created: [{}]", qType);
        return qType;
    }


    @RequestMapping(value = "/{qtid}", method = DELETE)
    public GenericResponse deleteQuestion(@PathVariable Integer qtid) {
        log.info("Deleting question type with qtid=[{}]", qtid);
        typeRepo.delete(qtid);
        return new GenericResponse("OK", "OK");
    }

}
