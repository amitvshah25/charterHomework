package com.charter.reward;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Getter @Setter
    private LocalDate transactionDate;
    @Getter @Setter @NonNull
    private Double transactionAmount = 0.0;
    @Getter @Setter
    private String transactionMonth;
    @Getter @Setter
    private Integer points = 0;
}
