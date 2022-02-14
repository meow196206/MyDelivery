package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.meow.dto.OrderDTO;
import ru.meow.dto.ProductDTO;
import ru.meow.model.Order;
import ru.meow.model.Product;
import ru.meow.repository.OrderRepository;
import ru.meow.repository.ProductRepository;
import ru.meow.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Override
    public List<OrderDTO> findByOrderId(Long id) {
        return orderRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого заказа " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        order.getProductList().add(product);
        orderRepository.save(order);
    }

    private OrderDTO map(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        List<ProductDTO> collect = order.getProductList().stream()
                .map(this::map)
                .collect(Collectors.toList());
        orderDTO.setProductList(collect);
        return orderDTO;
    }

    private ProductDTO map(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        return productDTO;
    }
}
