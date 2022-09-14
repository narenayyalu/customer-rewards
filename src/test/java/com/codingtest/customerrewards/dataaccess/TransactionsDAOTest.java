package com.codingtest.customerrewards.dataaccess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionsDAOTest {
	
	@InjectMocks
	TransactionsDAO transactionsDAO;

	@Test
	void givenJsonFile_whenGetTransactions_thenReturnTransactions() throws Exception {
		assertThat(transactionsDAO.getTransactions(123)).isNotEmpty();
	}

}
