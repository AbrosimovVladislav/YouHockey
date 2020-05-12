package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.TypeRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepo typeRepo;

    public Optional<Type> findByShowName(String showName){
        return typeRepo.findByShowName(showName);
    }

}
