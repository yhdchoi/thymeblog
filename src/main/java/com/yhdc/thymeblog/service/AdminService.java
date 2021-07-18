package com.yhdc.thymeblog.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final UserRepository userRepository;

	// Search and List User
	public Page<User> listUsers(String username, String email, Pageable pageable) {
		Page<User> users = userRepository.findByUsernameContainingOrEmailContaining(username, email, pageable);
		return users;
	}

	// TODO Refactoring
	public int getStartPage(Page<User> users) {
		int startPage = Math.max(1, users.getPageable().getPageNumber() - 9);
		return startPage;
	}

	public int getEndPage(Page<User> users) {
		int endPage = Math.min(users.getTotalPages(), users.getPageable().getPageNumber() + 9);
		return endPage;
	}

	// Detail
	public User detail(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("The user does not exist.");
		});
		return user;
	}

	// Create User
	public void register(User user) {
		// TODO Check Password
		// TODO RoleType selection
		userRepository.save(user);
	}

	// Delete User
	public String deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "The user [" + id + "] does not exist.";
		}
		return "The user [" + id + "] has been deleted";
	}
}
