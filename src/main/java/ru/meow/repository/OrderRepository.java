package ru.meow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meow.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser_Login(String login);

    void deleteByIdAndUser_Login(Long id, String login);

}
