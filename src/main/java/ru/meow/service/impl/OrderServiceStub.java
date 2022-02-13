package ru.meow.service.impl;

import ru.meow.dto.OrderDTO;
import ru.meow.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceStub implements OrderService {
    @Override
    public List<OrderDTO> findByOrderId(Long id) {
        return new ArrayList<>();
    }

    @Override
    public void addProductToOrder(Long orderId, Long productId) {
    }
}
