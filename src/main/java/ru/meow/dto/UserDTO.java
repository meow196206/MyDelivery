package ru.meow.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String login;
    private String password;
}
