package com.yhdc.thymeblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.repository.BoardRepository;
import com.yhdc.thymeblog.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardValidator boardValidator;

	// Search and List
	public Page<Board> listBoards(String title, String content, Pageable pageable) {

		Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);

		return boards;
	}

	public int getStartPage(Page<Board> boards) {

		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 9);

		return startPage;
	}

	public int getEndPage(Page<Board> boards) {

		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 9);

		return endPage;
	}

	// Detail
	public Board getDetail(Long id) {

		Board board = boardRepository.getById(id);

		return board;
	}

	// New
	public String register(Board board, BindingResult bindingResult) {

		boardValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			return "board/regierror";
		}

		boardRepository.save(board);
		return "";
	}

	// Update
	public Board updateForm(Long id) {

		Board board = boardRepository.findById(id).orElse(null);

		return board;
	}

	public void update(Board board) {

		boardRepository.save(board);
	}

	// Delete
	public void delete(Long id) {

		boardRepository.deleteById(id);
	}

}
