package com.yhdc.thymeblog.service;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;
import com.yhdc.thymeblog.validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserValidator userValidator;

	// Detail
	public User detail(Long id) {
		// Lambda
		User user = userRepository.findById(id).orElseThrow(() -> {			
				return new IllegalArgumentException("The user does not exist.");			
		});
		
		return user;
	}

	// New
	public String register(User user, BindingResult bindingResult) {

		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "board/regierror";
		}

		userRepository.save(user);
		return "";
	}

	// Update
	public User updateForm(Long id) {

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("The user does not exist.");
			}
		});

		return user;
	}

	public void update(User user) {

		userRepository.save(user);
	}

}
