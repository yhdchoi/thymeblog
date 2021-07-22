package com.yhdc.thymeblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {


	@GetMapping("/join")
	public String joinForm() {
		
		return "/auth/join";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "/auth/login";
	}
}
