package com.satish.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.satish.mockito.model.User;
import com.satish.mockito.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		System.out.println(user.getId()+" "+user.getName()+" "+user.getAge()+" "+user.getAddress());
		return userService.addUser(user);
	}
	
	@GetMapping("/getUsers")
	public List<User> getAllUsers(){
		List<User> users = userService.getUsers();
		System.out.println("userList: "+users.size());
		return users;
	}
	
	@GetMapping("/address")
	public List<User> getUserByAddress(@PathVariable("address") String address) {
		return userService.getUserByAddress(address);
	}
	
	@DeleteMapping("/delete")
	public void deleteUser(User user) {
		userService.deleteUser(user);
	}
	
}
