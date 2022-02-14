package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.UserDTO;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserDTO> newAll = new ArrayList<>();
        for (User user : all) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setLogin(user.getLogin());
        }
        return newAll;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        User save = userRepository.save(user);
        userDTO.setId(save.getId());
        return userDTO;
    }

    @Override
    public void deletedUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user1 = byId.get();
            user1.setLogin(userDTO.getLogin());
            user1.setName(userDTO.getName());
            user1.setPassword(userDTO.getPassword());
            User save = userRepository.save(user1);
            userDTO.setId(save.getId());
            return userDTO;
        } else {
            throw new IllegalArgumentException("Не смог найти id");
        }
    }

    @Override
    public void addProductToFavorites(Long userId, Long productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                User user = userOptional.get();
                user.getFavoritesList().add(product);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Не существует такого продукта " + productId);
            }
        } else {
            throw new IllegalArgumentException("Не существует такого пользователя " + userId);
        }
    }
}
