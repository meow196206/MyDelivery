package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.model.Order;
import ru.meow.model.Product;
import ru.meow.repository.OrderRepository;
import ru.meow.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @GetMapping("/users/{id}")
    public List<Order> findByUserId(@PathVariable Long id) {
        return orderRepository.findByUserId(id);
    }

    @PostMapping("{orderId}/product/{productId}")
    public Order addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                Order order = orderOptional.get();
                order.getProductList().add(product);
                return orderRepository.save(order);
            } else {
                throw new IllegalArgumentException("Не существует такого продукта " + productId);
            }
        } else {
            throw new IllegalArgumentException("Не существует такого заказа " + orderId);
        }
    }
}
