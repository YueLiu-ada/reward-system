package com.example.demo.controller;

import com.example.demo.model.CustomerTransaction;
import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
@ResponseBody
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(path = "/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Transaction getTransactionById(@PathVariable(name = "id") int id) {
        return transactionService.getById(id);
    }

    @GetMapping(path = "/customer/{id}")
    public List<Transaction> getTransactionsByCustomer(@PathVariable(name = "id") int id) {
        return transactionService.getByCustomer(id);
    }

    @GetMapping(path = "/search/{year}/{month}")
    public List<Transaction> getTransactionsByMonth(@PathVariable(name = "year") int year, @PathVariable(name = "month") int month) {
        return transactionService.getByMonth(year, month);
    }

    @PostMapping(path = "/add")
    public Transaction addTransaction(@RequestBody CustomerTransaction ct) {
        return transactionService.calculateReward(ct);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return null;
    }
}
