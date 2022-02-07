package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.model.Product;
import ru.meow.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product1 = byId.get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDescription(product.getDescription());
            return productRepository.save(product1);
        } else {
            throw new IllegalArgumentException("не смог найти id");
        }
    }

}
