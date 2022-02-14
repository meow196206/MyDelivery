package ru.meow.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.dto.StorageDTO;
import ru.meow.model.Product;
import ru.meow.model.Storage;
import ru.meow.repository.ProductRepository;
import ru.meow.repository.StorageRepository;
import ru.meow.service.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private StorageRepository storageRepository;
    private ProductRepository productRepository;

    @Override
    public List<StorageDTO> getAllStorage() {
        List<Storage> all = storageRepository.findAll();
        List<StorageDTO> newAll = new ArrayList<>();
        for (Storage storage : all) {
            StorageDTO storageDTO = new StorageDTO();
            storageDTO.setId(storage.getId());
            storageDTO.setName(storage.getName());
            storageDTO.setAddress(storage.getAddress());
            newAll.add(storageDTO);
        }
        return newAll;
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
        Optional<Storage> storageOptional = storageRepository.findById(storageId);
        if (storageOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                Storage storage = storageOptional.get();
                storage.getProductList().add(product);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Не существует такого продукта " + productId);
            }
        } else {
            throw new IllegalArgumentException("Не существует такого склада " + storageId);
        }
    }
}
