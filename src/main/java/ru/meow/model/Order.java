package ru.meow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders")

@Data
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            updatable = false)
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns =
            @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> productList;


}
