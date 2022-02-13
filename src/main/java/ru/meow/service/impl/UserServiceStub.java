package ru.meow.service.impl;

import org.springframework.stereotype.Service;
import ru.meow.dto.UserDTO;
import ru.meow.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceStub implements UserService {
    @Override
    public List<UserDTO> getAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return userDTO;
    }

    @Override
    public void deletedUser(Long id) {
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userDTO;
    }

    @Override
    public void addProductToFavorites(Long userId, Long productId) {
    }
}
