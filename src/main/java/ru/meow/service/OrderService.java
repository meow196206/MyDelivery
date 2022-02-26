package ru.meow.service;

import ru.meow.dto.OrderDTO;
import ru.meow.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findByLogin(String login);

    OrderDTO createOrder(String login, OrderDTO orderDTO);

    void deleteOrder(String login, Long id);

    List<OrderDTO> findByOrderId(Long id);

    void addProductToOrder(Long orderId, Long productId);

    void deleteProductFromOrder(Long orderId, Long productId);

    OrderDTO changeStatus(Long id, OrderStatus status);
}
