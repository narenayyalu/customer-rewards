package com.codingtest.customerrewards.dataaccess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.codingtest.customerrewards.model.Transactions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionsDAO {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public List<Transactions> getTransactions(int user) throws IOException {
		return getTransactions().stream().filter(transaction -> transaction.getUserId() == user).collect(Collectors.toList());
	}
	
	public List<Transactions> getTransactions() throws IOException {
		return objectMapper.readValue(new File("src/main/resources/transactions.json"), new TypeReference<List<Transactions>>(){});
	}

}
