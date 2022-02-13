package ru.meow.service.impl;

import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceStub implements ProductService {
    @Override
    public List<ProductDTO> getAllProduct() {
        return new ArrayList<>();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return productDTO;
    }

    @Override
    public void deletedProduct(Long id) {
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return productDTO;
    }
}
