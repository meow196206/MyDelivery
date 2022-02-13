package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.dto.OrderDTO;
import ru.meow.service.OrderService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/users/{id}")
    public List<OrderDTO> findByOrderId(@PathVariable Long id) {
        return orderService.findByOrderId(id);
    }

    @PostMapping("{orderId}/product/{productId}")
    public void addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        orderService.addProductToOrder(orderId, productId);
    }
}
