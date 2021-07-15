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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardControllerApi {

	private final BoardRepository boardRepository;

	// List
	@GetMapping("/list")
	public List<Board> list() {
		return boardRepository.findAll();
	}

	// Single board
	@GetMapping("/read/{id}")
	public Optional<Board> read(@PathVariable Long id) {

		return boardRepository.findById(id);
	}

	// Search title
	@GetMapping("/search")
	public List<Board> search(@RequestParam String title) {

		return boardRepository.findByTitle(title);
	}

	// Save new
	@PostMapping("/register")
	public Board registerBoard(@RequestBody Board newEmployee) {
		return boardRepository.save(newEmployee);
	}

	// Update
	@PutMapping("/update/{id}")
	public Board updateBoard(@RequestBody Board newBoard, @PathVariable Long id) {

		return boardRepository.findById(id).map(board -> {
			board.setTitle(newBoard.getTitle());
			board.setContent(newBoard.getContent());
			return boardRepository.save(board);
		}).orElseGet(() -> {
			newBoard.setId(id);
			return boardRepository.save(newBoard);
		});
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public void deleteBoard(@PathVariable Long id) {
		boardRepository.deleteById(id);
	}
}
