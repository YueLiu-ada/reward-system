package com.example.demo.model;

import lombok.Data;

@Data
public class CustomerTransaction {
    private int customerId;
    private double amount;

    public CustomerTransaction(int customerId, double amount){
        this.customerId = customerId;
        this.amount = amount;
    }
}
