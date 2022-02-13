package ru.meow.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int price;
    private String description;
}
