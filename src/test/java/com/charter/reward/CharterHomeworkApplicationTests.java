package com.charter.reward;

import static org.assertj.core.api.Assertions.assertThat;

import com.charter.reward.controllers.RewardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CharterHomeworkApplicationTests {
	@Autowired
	private RewardController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
