package com.charter.reward.controllers;

import com.charter.reward.models.Transaction;
import com.charter.reward.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
public class RewardController {
    @Autowired
    private RewardService rewardService;
    @GetMapping("/")
    public String index() {
        return "Charter Homework Assignment";
    }

    @PostMapping(value = "/reward", consumes = {"application/json"})
    public Map<String, Integer> calculateReward(@RequestBody List<Transaction> transactionList) {
        return rewardService.getMonthlyRewardPoints(transactionList);
    }
}