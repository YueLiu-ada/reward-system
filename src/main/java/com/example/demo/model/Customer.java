package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.*;
@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cid")
    private int cid;
    //@Column(name = "customer_name")
    private String name;
    @Column(name = "reward_points")
    private int points;

    @OneToMany
    @JoinColumn(name = "cid", foreignKey = @ForeignKey(name = "customerId_FK"))
    private List<Transaction> transactions;

    public Customer(int id, String name, int points){
        this.cid = id;
        this.name = name;
        this.points = points;
    }
}
