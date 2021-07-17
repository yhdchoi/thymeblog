package com.yhdc.thymeblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;
import com.yhdc.thymeblog.validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final UserRepository userRepository;
	private final UserValidator userValidator;

	// Search and List User
	public Page<User> listUsers(String username, String email, Pageable pageable) {

		Page<User> users = userRepository.findByUsernameContainingOrEmailContaining(username, email, pageable);

		return users;
	}

	public int getStartPage(Page<User> users) {

		int startPage = Math.max(1, users.getPageable().getPageNumber() - 9);

		return startPage;
	}

	public int getEndPage(Page<User> users) {

		int endPage = Math.min(users.getTotalPages(), users.getPageable().getPageNumber() + 9);

		return endPage;
	}
	
	// Create User
	
	public String register(User user, BindingResult bindingResult) {

		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "board/regierror";
		}

		userRepository.save(user);
		return "";
	}

	// Delete User
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
