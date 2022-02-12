package ru.meow.service;

import ru.meow.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDTO);

    void deletedUser(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void addProductToFavorites(Long userId, Long productId);
}
