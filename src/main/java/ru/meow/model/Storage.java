package ru.meow.model;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "storage")

@Data
public class Storage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String address;
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "storage_product",
            joinColumns =
            @JoinColumn(name = "storage_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> productList;
}
