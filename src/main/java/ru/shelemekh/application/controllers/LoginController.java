package ru.shelemekh.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "LoginPage";
	}

	@PostMapping("/logout")
	public String logout() {
		return "MainPage";
	}
}
