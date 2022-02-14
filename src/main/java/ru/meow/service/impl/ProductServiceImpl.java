package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.model.Product;
import ru.meow.repository.ProductRepository;
import ru.meow.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

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

    private ProductDTO map(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        return productDTO;
    }
}
