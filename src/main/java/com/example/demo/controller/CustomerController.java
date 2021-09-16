package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerReward;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
@Repository
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(path="/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping(path = "/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") int id) {
        return customerService.getCustomerById(id);
    }
    @GetMapping(path = "/reward/{id}")
    public CustomerReward getAwardsById(@PathVariable(name = "id") int id) {
        return customerService.getRewardById(id);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return null;
    }
}
