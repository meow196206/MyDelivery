package ru.meow.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders_products")
@Data
@IdClass(OrderProductId.class)
public class OrderProduct {
    @Column
    private Long count;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false,
            updatable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false,
            updatable = false)
    private Product product;
}
