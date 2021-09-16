package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerReward;
import com.example.demo.model.Reward;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM");

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id){
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerReward getRewardById(int id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) return null;
        List<Transaction> transactions = customer.getTransactions();
        List<Reward> rewards = new ArrayList<>();
        Reward reward = null;
        for(Transaction tran : transactions){

            String yearAndMonth = format.format(new Date(tran.getDate().getTime()));
            if(reward == null){
                reward = new Reward(yearAndMonth,tran.getReward());
            }
            else if(reward.getYearAndMonth().equals(yearAndMonth)){
                reward.setReward(reward.getReward() + tran.getReward());
            }
            else {
                rewards.add(reward);
                reward = new Reward(yearAndMonth, tran.getReward());
            }
        }
        if(reward != null) rewards.add(reward);
        return new CustomerReward(id, customer.getName(), customer.getPoints(),rewards);
    }
}
