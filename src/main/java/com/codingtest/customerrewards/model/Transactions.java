package com.codingtest.customerrewards.model;

import java.util.Date;

import lombok.Data;

@Data
public class Transactions {
	
	private int userId;
	private Date transactionDate;
	private int transactionAmount;
	private String transactionType;

}
