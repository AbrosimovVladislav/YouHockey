package ru.yourhockey.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.OfferRepo;
import ru.yourhockey.repo.ProductRepo;


@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateProductMinPriceTask {
    private static final int size = 100;
    private final ProductRepo productRepo;
    private final OfferRepo offerRepo;

    @Scheduled(cron = "0 */15 * * * ?")
    public void run() {
        log.info("UpdateProductMinPriceTask started");
/*        long start = System.currentTimeMillis();
        log.info("UpdateProductMinPriceTask was started");
        int page = 0;
        PageRequest pageRequest;
        Page<Product> productPage;
        do {
            pageRequest = PageRequest.of(page, size);
            productPage = productRepo.findAll(pageRequest);
            productPage.forEach(product -> {
                Double productMinPrice = offerRepo.calculateMinPriceByProduct(product.getProductId());
                product.setMinPrice(productMinPrice);
                productRepo.save(product);
            });
            page += 1;
        } while (productPage.getSize() == size);
        log.info("UpdateProductMinPriceTask was completed in {}", System.currentTimeMillis() - start);*/
    }
}
