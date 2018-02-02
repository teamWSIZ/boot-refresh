package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {
    Iterable<User> findByUsernameStartingWith(String usernameprefix);
}
