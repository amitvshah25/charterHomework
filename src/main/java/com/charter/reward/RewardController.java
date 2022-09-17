package com.charter.reward;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
public class RewardController {
    @GetMapping("/")
    public String index() {
        return "Charter Homework Assignment";
    }

    @PostMapping(value = "/reward", consumes = {"application/json"})
    public Map<String, Integer> calculateReward(@RequestBody List<Transaction> transactionList) {
        Map<String, List<Transaction>> groupedTransactions = transactionList
                .stream()
                .map(parseMonthName)
                .collect(Collectors.groupingBy(Transaction::getTransactionMonth));
        Map<String, Integer> monthlyRewardPointsMap = groupedTransactions.entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), e.getValue()
                        .stream()
                        .reduce(0, rewardPointsFromTransaction, Integer::sum)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue ));
        Integer overallTotal = monthlyRewardPointsMap.values().stream().reduce(0, Integer::sum);
        monthlyRewardPointsMap.put("OVERALL_TOTAL", overallTotal);
        return monthlyRewardPointsMap;
    }

    Integer getPoints(Double transactionAmount) {
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

    Function<Transaction, Transaction> parseMonthName = new Function<Transaction, Transaction>() {
        @Override
        public Transaction apply(Transaction t) {
            return new Transaction(
                    t.getTransactionDate(),
                    t.getTransactionAmount(),
                    t.getTransactionDate().getMonth().name(), getPoints(t.getTransactionAmount()));
        }
    };

    BiFunction<Integer, Transaction, Integer> rewardPointsFromTransaction = new BiFunction<Integer, Transaction, Integer>() {
        @Override
        public Integer apply(Integer rewardPoints, Transaction t) {
            return t.getPoints() + rewardPoints;
        }
    };
}