package com.charter.reward.services;

import org.springframework.stereotype.Service;

@Service
public class RewardService {
    public Integer getPoints(Double transactionAmount) {
        if (transactionAmount > 100) {
            Integer points = 50; // 1 point for above 50
            points += (transactionAmount.intValue() - 100) * 2; // 2 points for above 100
            return points;
        } else if (transactionAmount > 50) {
            return (transactionAmount.intValue() - 50); // 1 point for above 50
        } else {
            return 0;
        }
    }

}
