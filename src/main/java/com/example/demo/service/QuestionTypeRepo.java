package com.example.demo.service;

import com.example.demo.model.QType;
import org.springframework.data.repository.CrudRepository;

public interface QuestionTypeRepo extends CrudRepository<QType,Integer> {
}
