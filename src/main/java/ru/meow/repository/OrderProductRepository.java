package ru.meow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meow.model.Order;
import ru.meow.model.OrderProduct;
import ru.meow.model.OrderProductId;
import ru.meow.model.Product;

import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

    void removeByOrderAndProduct(Order order, Product product);

    Optional<OrderProduct> findByOrderAndProduct(Order order, Product product);

}
