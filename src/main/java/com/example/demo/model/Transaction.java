package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "reward")
    private int reward;
    @Column(name = "date")
    private Timestamp date;

    public Transaction(int customer_id, double amount, int reward, Timestamp date){
        this.customer_id = customer_id;
        this.amount = amount;
        this.reward = reward;
        this.date = date;
    }
}
