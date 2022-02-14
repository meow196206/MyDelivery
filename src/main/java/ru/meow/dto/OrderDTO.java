package ru.meow.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private long id;
    private List<ProductDTO> productList;
}
