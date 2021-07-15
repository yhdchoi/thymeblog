package com.yhdc.thymeblog.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.repository.BoardRepository;
import com.yhdc.thymeblog.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

	private final BoardRepository repository;
	private final BoardValidator validator;

	// List board
	@GetMapping("/list")
	public String list(Model model, Pageable pageable) {

		Page<Board> boards = repository.findAll(PageRequest.of(0, 10));
		
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 11);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 11);		
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);

		return "board/list";
	}

	// Single board
	@GetMapping("/read")
	public String read(Model model, @RequestParam Long id) {

		Board board = repository.getById(id);

		model.addAttribute("board", board);

		return "board/read";
	}

	// New Board form
	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("board", new Board());

		return "board/register";
	}

	// Save
	@PostMapping("/register")
	public String registerBoard(@Valid Board board, BindingResult bindingResult) {

		validator.validate(board, bindingResult);

		if (bindingResult.hasErrors()) {
			return "board/register";
		}

		repository.save(board);

		return "redirect:/board/list";
	}

	// Update form
	@GetMapping("/update")
	public String update(Model model) {

		model.addAttribute("board", new Board());

		return "board/update";
	}

	// Save update
	@PostMapping("/update")
	public String updateBoard(@ModelAttribute Board updateBoard, @RequestParam Long id) {

		Board board = repository.getById(id);

		board.setTitle(updateBoard.getTitle());
		board.setContent(updateBoard.getContent());

		repository.save(board);

		return "redirect:/board/read";
	}

	// Remove
	@DeleteMapping("/delete")
	public String deleteBoard(@RequestParam Long id) {

		repository.deleteById(id);

		return "redirect:/board/list";
	}
}
