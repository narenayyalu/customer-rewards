package com.codingtest.customerrewards.dataaccess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codingtest.customerrewards.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserDAO {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public Optional<User> getUser(int userId) throws IOException {
		return getUsers().stream()
				.filter(user -> user.getUserId() == userId).findFirst();
	}
	
	public List<User> getUsers() throws IOException  {
		return objectMapper.readValue(new File("src/main/resources/users.json"), new TypeReference<List<User>>(){});
	}

}
