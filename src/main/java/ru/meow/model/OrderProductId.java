package ru.meow.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderProductId implements Serializable {
    private Long order;
    private Long product;
}
