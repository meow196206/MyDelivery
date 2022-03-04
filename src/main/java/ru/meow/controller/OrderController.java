package ru.meow.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.meow.dto.OrderDTO;
import ru.meow.service.OrderService;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> findByLogin(Authentication auth) {
        return orderService.findByLogin(auth.getName());
    }

    @PostMapping("/")
    public OrderDTO createOrder(Authentication auth, @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(auth.getName(), orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(Authentication auth, @PathVariable Long id) {
        orderService.deleteOrder(auth.getName(), id);
    }

    @DeleteMapping("/{orderId}/product/{productId}")
    public void deleteProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        orderService.deleteProductFromOrder(orderId, productId);
    }

    @PostMapping("{orderId}/product/{productId}")
    public void addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        orderService.addProductToOrder(orderId, productId);
    }

    @PatchMapping("/{id}")
    public OrderDTO changeStatus(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.changeStatus(id, orderDTO.getStatus());
    }
}
