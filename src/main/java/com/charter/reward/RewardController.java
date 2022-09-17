package com.charter.reward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
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
    public Map<String, RewardPoints> calculateReward(@RequestBody List<Transaction> transactionList) {
        Map<String, List<Transaction>> groupedTransactions = transactionList
                .stream()
                .map(parseMonthName)
                .collect(Collectors.groupingBy(Transaction::getTransactionMonth));
        return groupedTransactions.entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), e.getValue()
                        .stream()
                        .reduce(new RewardPoints(), createReward, accumulateRewards)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue ));
    }

    private Integer getPoints(Double transactionAmount) {
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

    BiFunction<RewardPoints, Transaction, RewardPoints> createReward = new BiFunction<RewardPoints, Transaction, RewardPoints>() {
        @Override
        public RewardPoints apply(RewardPoints r, Transaction t) {
            return new RewardPoints(t.getPoints(), t.getTransactionAmount(), t.getTransactionMonth());
        }
    };

    BinaryOperator<RewardPoints> accumulateRewards = new BinaryOperator<RewardPoints>() {
        @Override
        public RewardPoints apply(RewardPoints r1, RewardPoints r2) {
            return new RewardPoints(r1.getTotalPoints() + r2.getTotalPoints(), r1.getTotalTransactionAmount() + r2.getTotalTransactionAmount(), r1.getMonthName());
        }
    };
}