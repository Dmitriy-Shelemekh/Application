package ru.shelemekh.application.controllers;

import org.apache.commons.collections4.MapUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.shelemekh.application.model.Message;
import ru.shelemekh.application.model.Role;
import ru.shelemekh.application.model.User;
import ru.shelemekh.application.repository.MessageRepository;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerTest {
    @Autowired
    private AppController appController;

    @Autowired
    private MessageRepository messageRepository;

    private Model model;
    private User user;
    private String filter;
    private String tag;
    private String messageText;
    private List<Message> messageList;

    @Before
    public void setUp() {
        model = Mockito.mock(Model.class);
        tag = "Tag";
        filter = "Filter";
        messageText = "MessageText";

        user = createUser();
        messageList = createMessageList();

        messageRepository = Mockito.mock(MessageRepository.class);
    }

    @After
    public void tearDown() {
        messageRepository = null;
    }

    @Test
    public void getMessages() {
        assertTrue("Перед началом теста модель дожна быть пустой", MapUtils.isEmpty(model.asMap()));

        Mockito.when(messageRepository.findByTag(tag)).thenReturn(messageList);
        appController.getMessages(filter, model);

        assertTrue("В модели должны быть сообщения", MapUtils.isNotEmpty(model.asMap()));
    }

    @Test
    public void addMessages() throws IOException {
        assertTrue("Перед началом теста модель дожна быть пустой", MapUtils.isEmpty(model.asMap()));

        MultipartFile file = Mockito.mock(MultipartFile.class);
        appController.addMessages(user, messageText, tag, model, file);

        assertTrue("В модели должны быть сообщения", MapUtils.isNotEmpty(model.asMap()));

        appController.deleteLastMessage();
    }

    @Test
    public void filterMessages() {
        appController.getMessages(filter, model);

        assertEquals("В модели не должно быть сообщений с тегом '" + tag + "'", 0, getMessageCount(model.asMap()));

        filter = tag;

        appController.getMessages(filter, model);

        assertEquals("", 0, getMessageCount(model.asMap()));
    }

    private int getMessageCount(Map<String, Object> model) {
        int size = -1;
        Object element = model.get("messages");

        if (element instanceof ArrayList) {
            size = ((ArrayList) element).size();
        }

        return size;
    }

    private List<Message> createMessageList() {
        Objects.requireNonNull(user, "User должен быть заполнен");

        List<Message> list = new ArrayList<Message>();

        list.add(new Message("Text#1", "Tag", user));
        list.add(new Message("Text#2", "Tag", user));
        list.add(new Message("Text#3", "Tag", user));

        return list;
    }

    private User createUser() {
        User user = new User();
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        user.setUsername("TestName");
        user.setPassword("pass");
        user.setId(1L);

        return user;
    }
}
