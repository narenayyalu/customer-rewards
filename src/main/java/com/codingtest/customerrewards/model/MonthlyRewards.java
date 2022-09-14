package com.codingtest.customerrewards.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class MonthlyRewards {
	
	private String month;
	private int monthlyRewards;

}
