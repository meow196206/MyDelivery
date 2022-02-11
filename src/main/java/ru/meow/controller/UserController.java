package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.model.Order;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user1 = byId.get();
            user1.setLogin(user.getLogin());
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            return userRepository.save(user1);
        } else {
            throw new IllegalArgumentException("не смог найти id");
        }
    }

    @PostMapping("{userId}/product/{productId}")
    public Product addProductToFavorites(@PathVariable Long userId, @PathVariable Long productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                User user = userOptional.get();
                user.getFavoritesList().add(product);
                return productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Не существует такого продукта " + productId);
            }
        } else {
            throw new IllegalArgumentException("Не существует такого пользователя " + userId);
        }
    }
}
