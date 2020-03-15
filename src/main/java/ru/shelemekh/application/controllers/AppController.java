package ru.shelemekh.application.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shelemekh.application.model.Message;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.repository.MessageRepository;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String getMessages(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Message> messages = messageRepository.findAll();

        if (StringUtils.isNotEmpty(filter)) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "AppPage";
    }

    @PostMapping
    public String addMessages(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model
    ) {
        Message message = new Message(text, tag, user);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "AppPage";
    }

    protected void deleteLastMessage() {
        messageRepository.delete(messageRepository.findTopByOrderByIdDesc());
    }
}
