package com.codingtest.customerrewards.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RewardsUtilTest {
	

	@Test
	void givenAmountOver100_whenCalculateRewards_thenReturnCorrectPoints() {
		assertThat(RewardsUtil.calculateRewards(120)).isEqualTo(90);
	}
	
	@Test
	void givenAmountOver50_whenCalculateRewards_thenReturnCorrectPoints() {
		assertThat(RewardsUtil.calculateRewards(51)).isEqualTo(1);
	}
	
	@Test
	void givenAmountLessThan50_whenCalculateRewards_thenReturnCorrectPoints() {
		assertThat(RewardsUtil.calculateRewards(41)).isEqualTo(0);
	}

}
