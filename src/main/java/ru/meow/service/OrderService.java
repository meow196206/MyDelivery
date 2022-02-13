package ru.meow.service;

import ru.meow.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findByOrderId(Long id);

    void addProductToOrder(Long orderId, Long productId);
}
