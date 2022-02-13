package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.meow.dto.OrderDTO;
import ru.meow.model.Order;
import ru.meow.model.Product;
import ru.meow.repository.OrderRepository;
import ru.meow.repository.ProductRepository;
import ru.meow.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Override
    public List<OrderDTO> findByOrderId(Long id) {
        List<Order> all = orderRepository.findAll();
        List<OrderDTO> newAll = new ArrayList<>();
        for (Order order : all) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
        }
        return newAll;
    }

    @Override
    public void addProductToOrder(Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                Order order = orderOptional.get();
                order.getProductList().add(product);
            } else {
                throw new IllegalArgumentException("Не существует такого продукта " + productId);
            }
        } else {
            throw new IllegalArgumentException("Не существует такого заказа " + orderId);
        }
    }
}
