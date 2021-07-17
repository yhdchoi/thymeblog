package com.yhdc.thymeblog.service;

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

	//TODO Refactoring
	public int getStartPage(Page<User> users) {
		int startPage = Math.max(1, users.getPageable().getPageNumber() - 9);
		return startPage;
	}

	public int getEndPage(Page<User> users) {
		int endPage = Math.min(users.getTotalPages(), users.getPageable().getPageNumber() + 9);
		return endPage;
	}
	
	// Create User	
	public String register(User user) {
		//TODO Check Password
		//TODO RoleType selection
		userRepository.save(user);
		return "";
	}

	// Delete User
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
