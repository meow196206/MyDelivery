package ru.meow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meow.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);


}
