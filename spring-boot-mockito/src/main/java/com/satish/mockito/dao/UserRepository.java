package com.satish.mockito.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.satish.mockito.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{

	public List<User> findByAddress(String address);

}
