package ru.shelemekh.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration() {
        return "RegistrationPage";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User Exist");
            return "RegistrationPage";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found");
        }

        return "LoginPage";
    }
}
