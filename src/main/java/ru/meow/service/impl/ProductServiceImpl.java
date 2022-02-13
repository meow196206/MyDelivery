package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.model.Product;
import ru.meow.repository.ProductRepository;
import ru.meow.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> all = productRepository.findAll();
        List<ProductDTO> newAll = new ArrayList<>();
        for (Product product : all) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
        }
        return newAll;
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
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product1 = byId.get();
            product1.setName(productDTO.getName());
            product1.setPrice(productDTO.getPrice());
            product1.setDescription(productDTO.getDescription());
            Product save = productRepository.save(product1);
            productDTO.setId(save.getId());
            return productDTO;
        } else {
            throw new IllegalArgumentException("Не смог найти id");
        }
    }
}
