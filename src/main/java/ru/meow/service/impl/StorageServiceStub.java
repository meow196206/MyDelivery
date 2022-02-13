package ru.meow.service.impl;

import org.springframework.stereotype.Service;
import ru.meow.dto.StorageDTO;
import ru.meow.service.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceStub implements StorageService {
    @Override
    public List<StorageDTO> getAllStorage() {
        return new ArrayList<>();
    }

    @Override
    public StorageDTO createStorage(StorageDTO storageDTO) {
        return storageDTO;
    }

    @Override
    public void deletedStorage(Long id) {
    }

    @Override
    public StorageDTO updateStorage(Long id, StorageDTO storageDTO) {
        return storageDTO;
    }

    @Override
    public void addProductToStorage(Long storageId, Long productId) {

    }
}
