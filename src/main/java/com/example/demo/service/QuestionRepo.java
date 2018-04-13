package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepo extends CrudRepository<Question,Integer> {
}
