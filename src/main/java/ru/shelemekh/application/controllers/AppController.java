package ru.shelemekh.application.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.shelemekh.application.model.Message;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.repository.MessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileUuid = UUID.randomUUID().toString();
            String filename = fileUuid + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + filename));

            message.setFilename(filename);
        }

        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "AppPage";
    }

    protected void deleteLastMessage() {
        messageRepository.delete(messageRepository.findTopByOrderByIdDesc());
    }
}
