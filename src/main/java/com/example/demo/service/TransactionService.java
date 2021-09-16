package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerTransaction;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction getById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public List<Transaction> getByCustomer(int id) {
        return transactionRepository.findAllByCustomerCidEquals(id);
    }
    public List<Transaction> getByMonth(int year, int month){
        if(year < 1970 || year > 3000 || month < 1 || month > 12) return null;
        int endYear = year, endMonth = month + 1;
        if(endMonth == 13) {
            endMonth = 1;
            endYear++;
        }
        long start = getFormatTime(year, month, 1);
        long end = getFormatTime(endYear, endMonth, 1);
        return transactionRepository.findTransactionsByDateBetween(new Timestamp(start), new Timestamp(end));
    }

    public Transaction calculateReward(CustomerTransaction customerTransaction){
        int id = customerTransaction.getCustomerId();
        double amount = customerTransaction.getAmount();
        int reward = 0;
        if(amount > 100){
            reward = ((int)amount - 100) * 2 + 50;
        }
        else if(amount > 50){
            reward = 50;
        }
        else reward = 0;
        Customer customer;
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
            customer.setPoints(customer.getPoints() + reward);
        }
        else{
            customer = new Customer(id, "Customer" + "_" + id, reward);
        }
        Transaction transaction = new Transaction(customer, amount, reward, new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);
        return transactionRepository.save(transaction);
    }
    public long getFormatTime(int year, int month, int day){
        String y = String.valueOf(year);
        String m = month < 10 ? "0"+month : String.valueOf(month);
        String d = day < 10 ? "0"+day : String.valueOf(day);
        Date date = null;
        try {
            date = format.parse(y+"-"+m+"-"+d +" 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
        return date.getTime();
    }
}
