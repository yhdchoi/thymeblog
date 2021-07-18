package com.yhdc.thymeblog.repotest;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yhdc.thymeblog.model.EnableType;
import com.yhdc.thymeblog.model.RoleType;
import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.repository.UserRepository;

@SpringBootTest
public class UserRepoTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void insert() {
		IntStream.rangeClosed(1, 20).forEach(i -> {
			User user = User.builder().username("User" + i).email("Email" + i + "@mail.com").password("password1234")
					.role(RoleType.USER).enable(EnableType.ENABLE).build();

			userRepository.save(user);
		});
	}

}
