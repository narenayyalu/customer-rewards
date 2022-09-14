package com.codingtest.customerrewards.dataaccess;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {
	
	@InjectMocks
	UserDAO userDAO;

	@Test
	void givenUserName_whenGetUser_thenReturnUser() throws IOException {
		assertThat(userDAO.getUser(123).get().getUserName()).isEqualTo("Narayanan Ayyalu");
	}
	
	@Test
	void givenUserNameIsNotPresent_whenGetUser_thenReturnUser() throws IOException {
		assertThat(userDAO.getUser(126).isPresent()).isFalse();
	}

}
