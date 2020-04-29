package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.shop.Shop;
import ru.yourhockey.repo.ShopRepo;


@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepo shopRepo;

    public Shop getById(long shopId) {
        return shopRepo.getOne(shopId);
    }

    public Shop update(Shop shop) {
        return shopRepo.saveAndFlush(shop);
    }
}
