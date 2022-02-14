package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.ProductDTO;
import ru.meow.dto.StorageDTO;
import ru.meow.model.Product;
import ru.meow.model.Storage;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.StorageRepository;
import ru.meow.service.StorageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private StorageRepository storageRepository;
    private ProductRepository productRepository;

    @Override
    public List<StorageDTO> getAllStorage() {
        return storageRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public StorageDTO createStorage(StorageDTO storageDTO) {
        Storage storage = new Storage();
        storage.setName(storageDTO.getName());
        storage.setAddress(storageDTO.getAddress());
        Storage save = storageRepository.save(storage);
        storageDTO.setId(save.getId());
        return storageDTO;
    }

    @Override
    public void deletedStorage(Long id) {
        storageRepository.deleteById(id);
    }

    @Override
    public StorageDTO updateStorage(Long id, StorageDTO storageDTO) {
        Optional<Storage> byId = storageRepository.findById(id);
        if (byId.isPresent()) {
            Storage storage1 = byId.get();
            storage1.setAddress(storageDTO.getAddress());
            storage1.setName(storageDTO.getName());
            Storage save = storageRepository.save(storage1);
            storageDTO.setId(save.getId());
            return storageDTO;
        } else {
            throw new IllegalArgumentException("не смог найти id");
        }
    }

    @Override
    public void addProductToStorage(Long storageId, Long productId) {
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого склада " + storageId));
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Не существует такого склада " + storageId));
        storageRepository.save(storage);
    }

    private StorageDTO map(Storage storage) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setAddress(storage.getAddress());
        storageDTO.setName(storage.getName());
        storageDTO.setId(storage.getId());
        return storageDTO;
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
