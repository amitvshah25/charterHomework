package com.charter.reward.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RewardServicesTest {
    @Autowired
    private RewardService rewardService;
    @Test
    void rewardCalculations() {
        assertThat(rewardService.getPoints(37.3)).isEqualTo(0);
        assertThat(rewardService.getPoints(99.99)).isEqualTo(49);
        assertThat(rewardService.getPoints(100.0)).isEqualTo(50);
        assertThat(rewardService.getPoints(101.0)).isEqualTo(52);
        assertThat(rewardService.getPoints(303.0)).isEqualTo(456);
    }
}
