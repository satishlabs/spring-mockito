package com.satish.mockito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satish.mockito.dao.UserRepository;
import com.satish.mockito.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getUsers(){
		List<User> users = userRepository.findAll();
		System.out.println("Getting data from DB: "+users);
		return users;
	}
	
	public List<User> getUserByAddress(String address){
		return userRepository.findByAddress(address);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
