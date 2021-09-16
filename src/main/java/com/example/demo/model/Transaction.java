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
//    @Column(name = "cid")
//    private int cid;
    @ManyToOne
    @JoinColumn(name="cid")
    private Customer customer;
    @Column(name = "amount")
    private double amount;
    @Column(name = "reward")
    private int reward;
    @Column(name = "date")
    private Timestamp date;

    public Transaction(Customer customer, double amount, int reward, Timestamp date){
        this.customer = customer;
        this.amount = amount;
        this.reward = reward;
        this.date = date;
    }
}
