package com.yhdc.thymeblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// Search
	Page<User> findByUsernameContainingOrEmailContaining(String username, String email, Pageable pageable);

}
