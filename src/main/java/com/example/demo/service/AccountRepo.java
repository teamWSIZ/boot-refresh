package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Crypto;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account,Integer> {
}
