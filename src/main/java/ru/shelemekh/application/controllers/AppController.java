package ru.shelemekh.application.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shelemekh.application.model.Message;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.repository.MessageRepository;

import java.util.Map;

@Controller
@RequestMapping
public class AppController {
	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("/app")
	public String getMessages(Map<String, Object> model) {
		Iterable<Message> messages = messageRepository.findAll();
		model.put("messages", messages);

		return "AppPage";
	}

	@PostMapping("/app")
	public String addMessages(
			@AuthenticationPrincipal User user,
			@RequestParam String text,
			@RequestParam String tag,
			Map<String, Object> model) {

		Message message = new Message(text, tag, user);
		messageRepository.save(message);
		Iterable<Message> messages = messageRepository.findAll();
		model.put("messages", messages);

		return "AppPage";
	}

	protected void deleteLastMessage() {
		messageRepository.delete(messageRepository.findTopByOrderByIdDesc());
	}

	@PostMapping("/filter")
	public String filterMessages(
			@RequestParam String filter,
			Map<String, Object> model) {

		Iterable<Message> messages;

		if (StringUtils.isNotEmpty(filter)) {
			messages = messageRepository.findByTag(filter);
		} else {
			messages = messageRepository.findAll();
		}

		model.put("messages", messages);

		return "AppPage";
	}
}
