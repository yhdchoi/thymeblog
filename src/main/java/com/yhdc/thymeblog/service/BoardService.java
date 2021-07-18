package com.yhdc.thymeblog.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	// Search List
	public Page<Board> boardSearchList(String title, String content, Pageable pageable) {
		Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);

		return boards;
	}

	// Detail
	public Board read(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE BOARD DOES NOT EXIST.");
		});
		return board;
	}

	// New Board
	public Board registerBoard(Board newBoard) {
		Board board = boardRepository.save(newBoard);

		return board;
	}

	// Update Board
	public Board updateBoard(Long id, Board newBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("THE BOARD DOES NOT EXIST.");
		});

		board.setTitle(newBoard.getTitle());
		board.setContent(newBoard.getContent());

		return board;
	}

	// Delete Board
	public String deleteBoard(Long id) {
		try {
			boardRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "THE BOARD DOES NOT EXIST.";
		}
		return "DELETED";
	}
}
