package com.codingtest.customerrewards.utils;

public class RewardsUtil {
	
	public static int calculateRewards(int transactionAmount) {
		
		int dollarAbove100 = transactionAmount > 100 ? transactionAmount - 100 : 0;
		int dollarAbove50 = transactionAmount > 50 ? transactionAmount - dollarAbove100 - 50 : 0;
		
		return (dollarAbove100 * 2) + dollarAbove50;
		
	}

}
