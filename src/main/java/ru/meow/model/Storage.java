package ru.meow.model;

import lombok.Data;

import javax.persistence.*;

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
}
