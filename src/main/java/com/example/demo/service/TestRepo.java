package com.example.demo.service;

import com.example.demo.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepo extends CrudRepository<Test,Integer> {
}
