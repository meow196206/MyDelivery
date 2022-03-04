package ru.meow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.dto.UserDTO;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не смог найти id"));
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User save = userRepository.save(user);
        return map(save);
    }

    private UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setFavoriteList(map(user.getFavoritesList()));
        return userDTO;
    }

    private List<ProductDTO> map(List<Product> productList) {
       return productList.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setName(product.getName());
            productDTO.setId(product.getId());
            return productDTO;
        }).collect(Collectors.toList());
    }
}
