package com.example.demo.service;

import com.example.demo.model.Crypto;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CryptoRepo extends CrudRepository<Crypto,Integer> {
}
