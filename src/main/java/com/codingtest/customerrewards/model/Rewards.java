package com.codingtest.customerrewards.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class Rewards {
	
	private int userId;
	private String userName;
	private int totalRewards;
	private List<MonthlyRewards> monthlyRewards;

}
