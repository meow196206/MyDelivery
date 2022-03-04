package ru.meow.dto;

import lombok.Data;

@Data
public class ProductCountDTO {
    private ProductDTO productDTO;
    private Long count;
}
