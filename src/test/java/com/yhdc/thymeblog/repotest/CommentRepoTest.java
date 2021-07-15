package com.yhdc.thymeblog.repotest;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yhdc.thymeblog.model.Comment;
import com.yhdc.thymeblog.repository.CommentRepository;

@SpringBootTest
public class CommentRepoTest {

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void insert() {

		IntStream.rangeClosed(1, 10).forEach(i -> {
			Comment comment = Comment.builder().content("Comment content..." + i).build();

			commentRepository.save(comment);
		});
	}
}
