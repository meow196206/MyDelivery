package ru.meow.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.dto.ProductDTO;
import ru.meow.service.ProductService;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping("/")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deletedProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

}
