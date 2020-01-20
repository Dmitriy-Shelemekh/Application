package ru.shelemekh.application.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shelemekh.application.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
