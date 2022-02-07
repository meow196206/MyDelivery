package ru.meow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meow.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
