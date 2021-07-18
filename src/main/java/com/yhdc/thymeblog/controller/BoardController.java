package com.yhdc.thymeblog.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	// Search and List
	@GetMapping("/list")
	public String list(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {

		Page<Board> boards = boardService.listBoards(searchText, searchText, pageable);
		int startPage = boardService.getStartPage(boards);
		int endPage = boardService.getEndPage(boards);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);

		return "board/list";
	}

	// Detail
	@GetMapping("/read")
	public String detail(Model model, @RequestParam Long id) {
		Board board = boardService.getDetail(id);
		model.addAttribute("board", board);
		return "board/read";
	}

	// New form
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("board", new Board());
		return "board/register";
	}

	// New save
	@PostMapping("/register")
	public String registerBoard(@Valid Board board) {
		boardService.register(board);
		return "redirect:/board/list";
	}

	// Update form
	@GetMapping("/update")
	public String updateForm(Model model, @RequestParam Long id) {
		Board board = boardService.updateForm(id);
		model.addAttribute("board", board);
		return "board/update";
	}

	// Update save
	@PostMapping("/update")
	public String updateBoard(@Valid Board board) {
		boardService.update(board);
		return "redirect:/board/list";
	}

	// Delete
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam Long id) {
		boardService.delete(id);
		return "redirect:/board/list";
	}
}
