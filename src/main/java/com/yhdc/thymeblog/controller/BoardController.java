package com.yhdc.thymeblog.controller;

import java.util.List;

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

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

	private final BoardRepository repository;
//	private final BoardValidator validator;

	// List board
	@GetMapping("/list")
	public String list(Model model) {

		List<Board> boards = repository.findAll();

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
	public String registerBoard(@ModelAttribute Board board) {

//		validator.validate(newBoard, bindingResult);
//		
//		if (bindingResult.hasErrors()) {
//			return "board/register";
//		}
		
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
	@DeleteMapping("/delete/{id}")
	public String deleteBoard(@RequestParam Long id) {

		repository.deleteById(id);

		return "redirect:/board/list";
	}
}
