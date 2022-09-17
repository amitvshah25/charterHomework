package com.charter.reward;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
class CharterHomeworkApplicationTests {
	@Autowired
	private RewardController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	@Test
	void rewardCalculations() {
		assertThat(controller.getPoints(37.3)).isEqualTo(0);
		assertThat(controller.getPoints(99.99)).isEqualTo(49);
		assertThat(controller.getPoints(100.0)).isEqualTo(50);
		assertThat(controller.getPoints(101.0)).isEqualTo(52);
		assertThat(controller.getPoints(303.0)).isEqualTo(456);
	}
}
