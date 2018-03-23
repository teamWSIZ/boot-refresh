package com.example.demo.controller;


import com.example.demo.model.Account;
import com.example.demo.model.Crypto;
import com.example.demo.model.User;
import com.example.demo.service.AccountRepo;
import com.example.demo.service.CryptoRepo;
import com.example.demo.service.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/accounts")
@Slf4j
public class AccountController {

    @Autowired
    AccountRepo accountRepo;


    @RequestMapping(value = "/status")
    public String showStatus() {
        return "App running OK";
    }


    @RequestMapping(value = "/", method = GET)
    public Iterable<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @RequestMapping(value = "/{aid}", method = GET)
    public Account getAccountById(@PathVariable Integer aid) {
        return accountRepo.findOne(aid);
    }

    @RequestMapping(value = "/", method = PUT)
    public Account updateOrCreateAccount(@RequestBody Account account) {
        if (account.getAid()==null) {
            log.info("Creating account {}", account);
        } else {
            log.info("Updating account {}", account);
        }
        return accountRepo.save(account);
    }

    @RequestMapping(value = "/{aid}", method = DELETE)
    public void deleteAccount(@PathVariable Integer aid) {
        log.info("Removing account {}", aid);
        accountRepo.delete(aid);
    }


}
