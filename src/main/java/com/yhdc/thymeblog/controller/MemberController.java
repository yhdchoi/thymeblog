package com.yhdc.thymeblog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhdc.thymeblog.model.Member;
import com.yhdc.thymeblog.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository repository;

	// List member ADMIN ONLY
	@GetMapping("/member/list")
	public String all(Model model) {

		List<Member> members = repository.findAll();

		model.addAttribute("members", members);

		return "member/list";
	}

	// Single member
	@GetMapping("/member/detail")
	public String one(@PathVariable Long id, Model model) {

		Member member = repository.getById(id);

		model.addAttribute("member", member);

		return "member/detail";
	}

	// Form
	@GetMapping("/member/register")
	public String form(Model model, @RequestParam(required = false) Long id) {

		if (id == null) {
			model.addAttribute("member", new Member());
		} else {
			Member member = repository.findById(id).orElse(null);
			model.addAttribute("member", member);
		}

		return "member/register";
	}

	// Save member // If param is present updated
	@PostMapping("/member/register")
	public String newMember(@ModelAttribute Member newMember) {

		repository.save(newMember);

		return "redirect:/";
	}

	// Update
//	@PutMapping("/update")
//	public String replaceMember(@ModelAttribute Member member, @PathVariable Long id) {
//
//		repository.save(member);
//		
//		return "redirect:member/detail";
//	}

	// Remove
	@DeleteMapping("/{id}")
	public String deleteMember(@RequestParam Long id) {

		repository.deleteById(id);

		return "redirect:member/list";
	}
}
