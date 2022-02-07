package ru.meow.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")

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
    private String password;
}
