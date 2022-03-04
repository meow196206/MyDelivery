package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meow.dto.ProductDTO;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return productDTO;
    }

    @Override
    public void deletedProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не смог найти id"));
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Product save = productRepository.save(product);
        return map(save);
    }

    @Override
    public void addProductToFavorites(String login, Long productId) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого пользователя " + login));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        user.getFavoritesList().add(product);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteProductToFavorites(String login, Long productId) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого пользователя " + login));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
        user.getFavoritesList().remove(product);
        userRepository.save(user);
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
