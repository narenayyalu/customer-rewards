package com.codingtest.customerrewards.service;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codingtest.customerrewards.dataaccess.TransactionsDAO;
import com.codingtest.customerrewards.dataaccess.UserDAO;
import com.codingtest.customerrewards.exception.UserNotFoundException;
import com.codingtest.customerrewards.model.MonthlyRewards;
import com.codingtest.customerrewards.model.Rewards;
import com.codingtest.customerrewards.model.Transactions;
import com.codingtest.customerrewards.model.User;
import com.codingtest.customerrewards.utils.RewardsUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardsService {
	
	private final UserDAO userDAO;
	private final TransactionsDAO transactionsDAO;
	
	public Rewards getRewards(int userId) throws UserNotFoundException, IOException {
		Optional<User> user =  userDAO.getUser(userId);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User "+ userId + "not found");
		}
		List<Transactions> transactions = transactionsDAO.getTransactions(userId);
		return buildRewards(user.get(), transactions);
	}

	private Rewards buildRewards(User user, List<Transactions> transactions) {
		Rewards rewards = Rewards.builder().userId(user.getUserId()).userName(user.getUserName()).build();
		Map<YearMonth, List<Transactions>> transactionsByMonth = groupByMonth(transactions);
		int totalRewards = 0;
		List<MonthlyRewards> monthlyRewards = new ArrayList<>();
		for(Map.Entry<YearMonth, List<Transactions>> entry : transactionsByMonth.entrySet() ) {
			MonthlyRewards monthlyReward = MonthlyRewards.builder().month(entry.getKey().toString()).build();
			int monthlyPoints = 0;
			for(Transactions transaction : entry.getValue()) {
				monthlyPoints = monthlyPoints + RewardsUtil.calculateRewards(transaction.getTransactionAmount());
			}
			totalRewards = totalRewards + monthlyPoints;
			monthlyReward.setMonthlyRewards(monthlyPoints);
			monthlyRewards.add(monthlyReward);
		}
		rewards.setTotalRewards(totalRewards);
		rewards.setMonthlyRewards(monthlyRewards);
		
		return rewards;
	} 
	
	private Map<YearMonth, List<Transactions>> groupByMonth(List<Transactions> transactions) {
		return transactions.stream()
				.collect(Collectors.groupingBy(transaction -> {
					Calendar cal = Calendar.getInstance();
					cal.setTime(transaction.getTransactionDate());
					return YearMonth.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
				}
				));	 	
	}

}
