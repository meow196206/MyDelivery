package ru.meow.model;

import lombok.Data;
import ru.meow.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders")

@Data
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProductList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            updatable = false)
    private User user;
}
