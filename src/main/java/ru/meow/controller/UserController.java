package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.dto.UserDTO;
import ru.meow.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deletedUser(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PostMapping("{userId}/product/{productId}")
    public void addProductToFavorites(@PathVariable Long userId, @PathVariable Long productId) {
        userService.addProductToFavorites(userId, productId);
    }
}
