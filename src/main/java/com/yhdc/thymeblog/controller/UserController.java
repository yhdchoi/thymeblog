package com.yhdc.thymeblog.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	// Search and List User
	@GetMapping("/list")
	public ResponseEntity<Page<User>> userSearchList(@RequestParam String username, @RequestParam String email,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> users = userRepository.findByUsernameContainingOrEmailContaining(username, email, pageable);
		// List<User> //.getContent();

		return new ResponseEntity<Page<User>>(users, HttpStatus.OK);
	}

	// Detail
	@GetMapping("/detail/{id}")
	public ResponseEntity<User> detail(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE USER DOES NOT EXIST.");
		});
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// New User
	@PostMapping("/register")
	public ResponseEntity<User> newUser(@Valid @RequestBody User newUser) {
		User user = userRepository.save(newUser);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Update User
	@Transactional
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody User updateUser) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE USER DOES NOT EXIST.");
		});

		user.setEmail(updateUser.getEmail());
		user.setPassword(updateUser.getPassword());

		return new ResponseEntity<String>("UPDATED", HttpStatus.OK);
	}

	// Delete User
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("THE USER DOES NOT EXIST.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("DELETED", HttpStatus.OK);
	}
}
