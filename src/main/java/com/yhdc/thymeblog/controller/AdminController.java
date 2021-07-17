package com.yhdc.thymeblog.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhdc.thymeblog.model.User;
import com.yhdc.thymeblog.service.AdminService;
import com.yhdc.thymeblog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;
	private final UserService userService;

	// User List
	@GetMapping("/ulist")
	public String list(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		Page<User> users = adminService.listUsers(searchText, searchText, pageable);
		model.addAttribute("users", users);
		return "admin/ulist";
	}

	// Detail
	@GetMapping("/udetail")
	public String detail(Model model, @RequestParam Long id) {
		User user = userService.detail(id);
		model.addAttribute("user", user);
		return "admin/udetail";
	}

	// Create User
	@GetMapping("/ujoin")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "admin/ujoin";
	}

	@PostMapping("/ujoin")
	public String registerUser(@Valid User user, BindingResult bindingResult) {
		adminService.register(user, bindingResult);
		return "redirect:/admin/ulist";
	}

	// User Delete
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		adminService.deleteUser(id);
		return "redirect:/admin/ulist";
	}
}
