package com.example.demo.model;

import lombok.Data;

@Data
public class Reward {
    private String yearAndMonth;
    private int reward;

    public Reward(String yearAndMonth, int reward){
        this.yearAndMonth = yearAndMonth;
        this.reward = reward;
    }
}
