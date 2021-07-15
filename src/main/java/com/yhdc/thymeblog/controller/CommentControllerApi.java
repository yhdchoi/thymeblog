package com.yhdc.thymeblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.thymeblog.model.Comment;
import com.yhdc.thymeblog.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentControllerApi {

	private final CommentRepository commentRepository;

	// List
	@GetMapping("/list")
	public List<Comment> all() {
		return commentRepository.findAll();
	}

	// Single comment
	@GetMapping("/read/{id}")
	public Optional<Comment> read(@PathVariable Long id) {

		return commentRepository.findById(id);
	}

	@PostMapping("/register")
	public Comment registerComment(@RequestBody Comment newEmployee) {
		return commentRepository.save(newEmployee);
	}

	// Update comment
	@PutMapping("/update/{id}")
	public Comment updateComment(@RequestBody Comment newComment, @PathVariable Long id) {

		return commentRepository.findById(id).map(comment -> {
			comment.setContent(newComment.getContent());
			return commentRepository.save(comment);
		}).orElseGet(() -> {
			newComment.setId(id);
			return commentRepository.save(newComment);
		});
	}

	// Delete comment
	@DeleteMapping("/delete/{id}")
	public void deleteComment(@PathVariable Long id) {
		commentRepository.deleteById(id);
	}
}
