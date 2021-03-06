package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.ProductRepo;
import ru.yourhockey.service.client.TrustInfoClient;
import ru.yourhockey.service.logging.MeasurePerformance;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OfferService offerService;
    private final TrustInfoClient trustInfoClient;

    @MeasurePerformance
    public List<Product> recalculateMinMaxPrice() {
        List<Product> all = productRepo.findAll();
        return all.stream()
                .filter(p -> p.getOffer() != null && !p.getOffer().isEmpty())
                .map(p -> {
                    p.setMinPrice(offerService.calculateMinPriceByProduct(p.getProductId()));
                    p.setMaxPrice(offerService.calculateMaxPriceByProduct(p.getProductId()));
                    return productRepo.save(p);
                })
                .collect(Collectors.toList());
    }

    public List<Product> getAllByParameters(Map<String, String> requestParams, Pageable pageable) {
        return productRepo.findAllByParameters(requestParams, pageable, Product.class);
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(long id) {
        return productRepo.getOne(id);
    }

    @MeasurePerformance
    public Product troubleTicketCreateProduct(Product product) {
        Product saved = productRepo.troubleTicketSaveOrUpdate(product);
        trustInfoClient.saveToTrustInfo(saved);
        return saved;
    }

    public List<Product> search(String searchLine) {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(p -> Pair.of(
                    p,
                    String.join(
                        " ",
                        List.of(
                            p.getType().getShowName(),
                            p.getBrand().getShortName(),
                            p.getModel(),
                            p.getAge().name()
                        )
                    )
                ))
                .filter(pair -> pair.getSecond().toUpperCase().contains(searchLine.toUpperCase()))
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }

    public Product update(Product product) {
        return productRepo.saveAndFlush(product);
    }
}
