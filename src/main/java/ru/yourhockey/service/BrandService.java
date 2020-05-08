package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.repo.BrandRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepo brandRepo;

    public List<Brand> getAll() {
        return brandRepo.findAll();
    }

}
