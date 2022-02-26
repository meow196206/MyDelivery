package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.OrderDTO;
import ru.meow.dto.ProductDTO;
import ru.meow.enums.OrderStatus;
import ru.meow.exception.NotFoundUserException;
import ru.meow.model.Order;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.OrderRepository;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public List<OrderDTO> findByLogin(String login) {
        return orderRepository.findAllByUser_Login(login).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(String login, OrderDTO orderDTO) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        User user = byLogin.orElseThrow(() -> new NotFoundUserException("Не существует такого юзера"));
        Order order = map(orderDTO, user);
        orderRepository.save(order);
        return map(order);
    }

    @Override
    @Transactional
    public void deleteOrder(String login, Long id) {
        orderRepository.deleteByIdAndUser_Login(id, login);
    }

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

    @Override
    public void deleteProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого заказа " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        order.getProductList().remove(product);
        orderRepository.save(order);
    }

    @Override
    public OrderDTO changeStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("не сущетсвует такого заказа " + id));
        order.setStatus(status);
        orderRepository.save(order);
        return map(order);
    }

    private OrderDTO map(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCreateDate(order.getCreatedDate());
        List<ProductDTO> collect = order.getProductList().stream()
                .map(this::map)
                .collect(Collectors.toList());
        orderDTO.setProductList(collect);
        return orderDTO;
    }

    private Order map(OrderDTO orderDTO, User user) {
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setCreatedDate(LocalDateTime.now());
        order.setId(orderDTO.getId());
        order.setUser(user);
        return order;
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
