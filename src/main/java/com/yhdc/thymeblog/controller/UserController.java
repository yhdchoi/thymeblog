package com.yhdc.thymeblog.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// Detail
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam Long id) {
		User user = userService.detail(id);
		model.addAttribute("user", user);
		return "user/detail";
	}

	// Update
	@GetMapping("/update")
	public String updateForm(Model model, @RequestParam Long id) {
		User user = userService.updateForm(id);		
		
		model.addAttribute("user", user);
		return "user/update";
	}

	// Update save
	@PostMapping("/update")
	public String update(@Valid User user) {
		userService.update(user);
		return "redirect:/user/detail";
	}

}
