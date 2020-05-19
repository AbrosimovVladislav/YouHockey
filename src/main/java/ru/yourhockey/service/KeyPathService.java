package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.filtration.KeyPath;
import ru.yourhockey.repo.KeyPathRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeyPathService {

    private final KeyPathRepo keyPathRepo;

    public void create(KeyPath kp) {
        keyPathRepo.save(kp);
    }

    public KeyPath getById(Long id) {
        return keyPathRepo.findById(id).orElseThrow();
    }
}
