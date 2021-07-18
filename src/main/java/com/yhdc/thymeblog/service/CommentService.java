package com.yhdc.thymeblog.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.yhdc.thymeblog.model.Comment;
import com.yhdc.thymeblog.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	// Search List
	public Page<Comment> boardSearchList(String content,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Comment> comments = commentRepository.findByContentContaining(content, pageable);

		return comments;
	}

	// Detail
	public Comment read(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE COMMENT DOES NOT EXIST.");
		});
		return comment;
	}

	// New Comment
	public Comment registerBoard(Comment newComment) {
		Comment comment = commentRepository.save(newComment);

		return comment;
	}

	// Update Comment
	public Comment updateBoard(Long id, Comment newComment) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE COMMENT DOES NOT EXIST.");
		});
		comment.setContent(newComment.getContent());

		return comment;
	}

	// Delete Comment
	public String deleteComment(Long id) {
		try {
			commentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "THE COMMENT DOES NOT EXIST.";
		}
		return "DELETED";
	}
}
