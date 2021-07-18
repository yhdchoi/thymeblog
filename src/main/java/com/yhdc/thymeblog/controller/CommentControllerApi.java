package com.yhdc.thymeblog.controller;

import java.util.List;

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
	public ResponseEntity<List<Comment>> all() {
		List<Comment> comments = commentRepository.findAll();

		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	// Search List
	@GetMapping("/list")
	public ResponseEntity<Page<Comment>> boardSearchList(@RequestParam String content,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Comment> comments = commentRepository.findByContentContaining(content, pageable);

		return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
	}

	// Detail
	@GetMapping("/read/{id}")
	public ResponseEntity<Comment> read(@PathVariable Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE COMMENT DOES NOT EXIST.");
		});

		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	// New Comment
	@PostMapping("/register")
	public ResponseEntity<Comment> registerBoard(@Valid @RequestBody Comment newComment) {
		Comment comment = commentRepository.save(newComment);

		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	// Update Comment
	@Transactional
	@PutMapping("/update/{id}")
	public ResponseEntity<Comment> updateBoard(@PathVariable Long id, @Valid @RequestBody Comment newComment) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE COMMENT DOES NOT EXIST.");
		});

		comment.setContent(newComment.getContent());

		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	// Delete Comment
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
		try {
			commentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("THE COMMENT DOES NOT EXIST.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("DELETED", HttpStatus.OK);
	}
}
