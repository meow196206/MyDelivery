package ru.meow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meow.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    Storage findByName(String name);
}
