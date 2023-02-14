package com.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public interface UserService {

	User saveUser(User user);

	List<User> getAlluser();

	User getUserById(String id);

	User updateUser(User user, String id);

	void deleteUser(String id);
}
