package ru.meow.controller;

import org.springframework.web.bind.annotation.*;
import ru.meow.model.User;
import ru.meow.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/")
    public List<User> getAllUsers(){
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
}
