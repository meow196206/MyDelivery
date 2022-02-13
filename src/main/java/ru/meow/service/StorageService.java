package ru.meow.service;

import ru.meow.dto.StorageDTO;

import java.util.List;

public interface StorageService {

    List<StorageDTO> getAllStorage();

    StorageDTO createStorage(StorageDTO storageDTO);

    void deletedStorage(Long id);

    StorageDTO updateStorage(Long id, StorageDTO storageDTO);

    void addProductToStorage(Long storageId, Long productId);
}
