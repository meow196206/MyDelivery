package ru.meow.service;

import ru.meow.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();

    ProductDTO createProduct(ProductDTO productDTO);

    void deletedProduct(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);
}
