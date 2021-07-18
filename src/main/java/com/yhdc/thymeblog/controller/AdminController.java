package com.yhdc.thymeblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.thymeblog.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

}
