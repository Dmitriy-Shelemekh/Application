package ru.shelemekh.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shelemekh.application.model.Role;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "RegistrationPage";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser != null) {
            model.addAttribute("message", "User Exist");
            return "RegistrationPage";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
