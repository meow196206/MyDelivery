package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meow.dto.OrderDTO;
import ru.meow.dto.ProductCountDTO;
import ru.meow.dto.ProductDTO;
import ru.meow.enums.OrderStatus;
import ru.meow.exception.NotFoundUserException;
import ru.meow.model.Order;
import ru.meow.model.OrderProduct;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.OrderProductRepository;
import ru.meow.repository.OrderRepository;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderProductRepository orderProductRepository;

    @Override
    public List<OrderDTO> findByLogin(String login) {
        return orderRepository.findAllByUser_Login(login).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDTO createOrder(String login, OrderDTO orderDTO) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        User user = byLogin.orElseThrow(() -> new NotFoundUserException("Не существует такого юзера"));
        Order order = map(orderDTO, user);
        orderRepository.save(order);
        return map(order);
    }

    @Override
    @Transactional(readOnly = false)
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
    @Transactional(readOnly = false)
    public void addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого заказа " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        Optional<OrderProduct> byOrderAndProduct = orderProductRepository.findByOrderAndProduct(order, product);
        if (byOrderAndProduct.isPresent()) {
            OrderProduct orderProduct = byOrderAndProduct.get();
            orderProduct.setCount(orderProduct.getCount() + 1);
            orderProductRepository.save(orderProduct);
        } else {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            orderProduct.setCount(1L);
            orderProductRepository.save(orderProduct);
            order.getOrderProductList().add(orderProduct);
            orderRepository.save(order);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого заказа " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        Optional<OrderProduct> byOrderAndProduct = orderProductRepository.findByOrderAndProduct(order, product);
        if (byOrderAndProduct.isPresent()) {
            OrderProduct orderProduct = byOrderAndProduct.get();
            if (orderProduct.getCount() > 1) {
                orderProduct.setCount(orderProduct.getCount() - 1);
                orderProductRepository.save(orderProduct);
            } else {
                orderProductRepository.removeByOrderAndProduct(order, product);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDTO changeStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("не сущетсвует такого заказа " + id));
        order.setStatus(status);
        orderRepository.save(order);
        return map(order);
    }

    private ProductCountDTO map(OrderProduct orderProduct) {
        ProductCountDTO productCountDTO = new ProductCountDTO();
        productCountDTO.setCount(orderProduct.getCount());
        productCountDTO.setProductDTO(map(orderProduct.getProduct()));
        return productCountDTO;
    }

    private OrderDTO map(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCreateDate(order.getCreatedDate());
        List<ProductCountDTO> collect = order.getOrderProductList().stream()
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
