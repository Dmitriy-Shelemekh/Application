package ru.shelemekh.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "LoginPage.mustache";
	}

	@PostMapping("/logout")
	public String logout() {
		return "MainPage.mustache";
	}
}
