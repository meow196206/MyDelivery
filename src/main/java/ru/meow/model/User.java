package ru.meow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")

@Data
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String login;
    @Column
    private String name;
    @Column
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @ManyToMany
    @JoinTable(name = "favorites_products",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> favoritesList;
}
