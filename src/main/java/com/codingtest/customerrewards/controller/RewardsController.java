package com.codingtest.customerrewards.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.codingtest.customerrewards.exception.UserNotFoundException;
import com.codingtest.customerrewards.model.Rewards;
import com.codingtest.customerrewards.service.RewardsService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RewardsController {
	
	private final RewardsService rewardsService;
	
	@GetMapping("/rewards/user/{userId}")
	public Rewards getRewards(@PathVariable int userId) throws UserNotFoundException, IOException {
		return rewardsService.getRewards(userId);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Rewards> handleUserNotFound() {
		return ResponseEntity.notFound().build();
	}

}
