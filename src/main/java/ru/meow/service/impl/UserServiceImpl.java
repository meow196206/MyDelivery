package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.dto.UserDTO;
import ru.meow.model.Product;
import ru.meow.model.User;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.UserRepository;
import ru.meow.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProductRepository productRepository;

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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого пользователя " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого продукта " + productId));
    }

    private UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
//        List<ProductDTO> collect = user.getFavoritesList().stream()
//                .map(this::map)
//                .collect(Collectors.toList());
//        userDTO.setFavoritList(collect);
        return userDTO;
    }

    private ProductDTO map(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        return productDTO;
    }
}
