package com.codingtest.customerrewards.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.codingtest.customerrewards.dataaccess.TransactionsDAO;
import com.codingtest.customerrewards.dataaccess.UserDAO;
import com.codingtest.customerrewards.model.Rewards;
import com.codingtest.customerrewards.service.RewardsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RewardsController.class)
@ExtendWith(SpringExtension.class)
@Import({RewardsService.class, UserDAO.class, TransactionsDAO.class})
class RewardsControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void givenUserId_whenGetRewards_thenReturnRewards() throws Exception {
		MvcResult result = mvc.perform(get("/rewards/user/{userId}", 123)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andReturn();
		
		Rewards rewards = objectMapper.readValue(result.getResponse().getContentAsString(), Rewards.class);
		
		assertThat(rewards.getUserId()).isEqualTo(123);
		assertThat(rewards.getUserName()).isEqualTo("Narayanan Ayyalu");
		assertThat(rewards.getTotalRewards()).isEqualTo(541);
		
	}
	
	@Test
	void givenUserIdNotFound_whenGetRewards_thenReturn404() throws Exception {
		mvc.perform(get("/rewards/user/{userId}", 129)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
		
		
	}

}
