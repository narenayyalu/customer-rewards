package com.codingtest.customerrewards.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codingtest.customerrewards.dataaccess.TransactionsDAO;
import com.codingtest.customerrewards.dataaccess.UserDAO;
import com.codingtest.customerrewards.exception.UserNotFoundException;
import com.codingtest.customerrewards.model.MonthlyRewards;
import com.codingtest.customerrewards.model.Rewards;

@ExtendWith(SpringExtension.class)
@Import({UserDAO.class, TransactionsDAO.class, RewardsService.class})
class RewardsServiceTest {
	
	@Autowired
	RewardsService rewardsService;

	@Test
	void givenUser_whenGetRewards_thenReturnRewards() throws IOException, UserNotFoundException{
		MonthlyRewards julyReward = MonthlyRewards.builder().month("2022-07").monthlyRewards(0).build();
		MonthlyRewards augReward = MonthlyRewards.builder().month("2022-08").monthlyRewards(450).build();
		MonthlyRewards sepReward = MonthlyRewards.builder().month("2022-09").monthlyRewards(91).build();
		Rewards rewards = rewardsService.getRewards(123);
		assertThat(rewards.getUserId()).isEqualTo(123);
		assertThat(rewards.getTotalRewards()).isEqualTo(541);
		assertThat(rewards.getMonthlyRewards()).containsOnly(julyReward, augReward, sepReward);
	}
	
	@Test
	void givenUnknownUser_whenGetRewards_thenThrowUserNotFoundException() {
		assertThrows(UserNotFoundException.class, () -> { rewardsService.getRewards(127);});
	}

}

