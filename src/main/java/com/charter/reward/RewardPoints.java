package com.charter.reward;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RewardPoints {
    @Getter @Setter private Integer totalPoints;
    @Getter @Setter private Double totalTransactionAmount;
    @Getter @Setter private String monthName;
}
