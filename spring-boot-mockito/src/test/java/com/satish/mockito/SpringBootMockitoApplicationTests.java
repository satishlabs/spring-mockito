package com.satish.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.satish.mockito.dao.UserRepository;
import com.satish.mockito.model.User;
import com.satish.mockito.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootMockitoApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	
	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "Satish", 33, "Ranchi"), new User(2, "Manish", 12, "Patna")).collect(Collectors.toList()));
		assertEquals(2, userService.getUsers().size());
	}
	
	@Test
	public void getUserByAddressTest() {
		String address = "Banaglore";
		when(userRepository.findByAddress(address)).thenReturn(Stream.of(new User(1, "Kumar", 32, "USA")).collect(Collectors.toList()));
		assertEquals(1, userService.getUserByAddress(address).size());
	}
	
	@Test
	public void saveUserTest() {
		User user = new User(11, "Ms", 37, "Pune");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.addUser(user));
	}
	
	@Test
	public void deleteUserTest() {
		User user = new User(22, "Virat", 31, "Delhi");
		userService.deleteUser(user);
		verify(userRepository, timeout(1)).delete(user);
	}
}
