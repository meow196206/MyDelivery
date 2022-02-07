package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meow.model.Storage;
import ru.meow.repository.StorageRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/storage")
public class StorageController {

    private StorageRepository storageRepository;

    @GetMapping("/")
    public List<Storage> getAllStorage() {
        return storageRepository.findAll();
    }

    @PostMapping("/")
    public Storage createStorage(@RequestBody Storage storage) {
        return storageRepository.save(storage);
    }

    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        storageRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Storage updateStorage(@PathVariable Long id, @RequestBody Storage storage) {
        Optional<Storage> byId = storageRepository.findById(id);
        if (byId.isPresent()) {
            Storage storage1 = byId.get();
            storage1.setName(storage.getName());
            storage1.setAddress(storage.getAddress());
            return storageRepository.save(storage1);
        } else {
            throw new IllegalArgumentException("не смог найти id");
        }
    }
}
