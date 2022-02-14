package ru.meow.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String login;
    private String password;
    private List<ProductDTO> favoritList;
}
