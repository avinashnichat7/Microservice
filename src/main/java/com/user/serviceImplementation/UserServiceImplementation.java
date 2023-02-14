package com.user.serviceImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exception.NotFoundUserException;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setId(randomUserId);
		return userRepository.save(user);

	}

	@Override
	public List<User> getAlluser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundUserException("user with " + id + "doesnt exist"));

	}

	@Override
	public User updateUser(User user, String id) {
		User existingUser = userRepository.findById(id).get();
		if (user.getName() != null) {

			existingUser.setName(user.getName());

		}
		if (user.getEmail() != null) {
			existingUser.setEmail(user.getEmail());
		}
		if (user.getAbout() != null) {
			existingUser.setAbout(user.getAbout());
		}
		return userRepository.save(existingUser);

//		return userRepository.findById(id).flatMap(existingUser -> {
//
//			// orElseThrow(()-> new NotFoundUserException("user with " + id + "doesnt
//			// exist"));
//
//			existingUser.setEmail(Objects.nonNull(user.getEmail()) ? user.getEmail() : existingUser.getEmail());
//			existingUser.setName(Objects.nonNull(user.getName()) ? user.getName() : existingUser.getName());
//			existingUser.setAbout(Objects.nonNull(user.getAbout()) ? user.getAbout() : existingUser.getAbout());
//			User save = userRepository.save(existingUser);
//
//		});
	}

	@Override
	public void deleteUser(String id) {

		userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("user with " + id + "doesnt exist"));
	}

}
