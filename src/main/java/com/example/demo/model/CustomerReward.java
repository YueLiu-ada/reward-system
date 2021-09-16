package com.example.demo.model;

import lombok.Data;

import java.util.*;
@Data
public class CustomerReward {
    private int customer_id;
    private String name;
    private int rewardTotal;
    private List<Reward> rewards;

    public CustomerReward(int customer_id, String name, int rewardTotal, List<Reward> rewards){
        this.customer_id = customer_id;
        this.name = name;
        this.rewardTotal = rewardTotal;
        this.rewards = rewards;
    }
}
