package ru.shelemekh.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shelemekh.application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);

    User findByActivationCode(String code);
}
