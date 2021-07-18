package com.yhdc.thymeblog.service;

import org.springframework.stereotype.Service;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	// Search and List

	// Detail
	public User detail(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("The user does not exist.");
		});
		return user;
	}

	// New
	public void register(User user) {
		// TODO Check Password
		userRepository.save(user);

	}

	// Update
	public User updateForm(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("The user does not exist.");
		});

		return user;
	}

	public void update(User user) {

		userRepository.save(user);
	}

	// Delete

}
