package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.dto.StorageDTO;
import ru.meow.service.StorageService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/storage")
public class StorageController {
    private StorageService storageService;

    @GetMapping("/")
    public List<StorageDTO> getAllStorage() {
        return storageService.getAllStorage();
    }

    @PostMapping("/")
    public StorageDTO createStorage(@RequestBody StorageDTO storageDTO) {
        return storageService.createStorage(storageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        storageService.deletedStorage(id);
    }

    @PutMapping("/{id}")
    public StorageDTO updateStorage(@PathVariable Long id, @RequestBody StorageDTO storageDTO) {
        return storageService.updateStorage(id, storageDTO);
    }

    @PostMapping("{storageId}/product/{productId}")
    public void addProductToStorage(@PathVariable Long storageId, @PathVariable Long productId) {
        storageService.addProductToStorage(storageId, productId);
    }
}
