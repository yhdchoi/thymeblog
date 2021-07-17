package com.yhdc.thymeblog.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	// Login
	@GetMapping("/login")
	public String login(Model model) {
//		model.addAttribute("user", user);
		return "auth/login";
	}

	@PostMapping("/login")
	public String loginSubmit() {
		//
		return "redirect:/";
	}

	// New
	@GetMapping("/join")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "user/join";
	}

	// New save
	@PostMapping("/join")
	public String registerUser(@Valid User user, BindingResult bindingResult) {
		userService.register(user, bindingResult);
		return "redirect:/";
	}

	// Logout
	@GetMapping("/logout")
	public String logoutPage() {
		return "auth/logout";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}


}
