package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Age;
import ru.yourhockey.service.BrandService;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.service.TypeService;
import ru.yourhockey.web.TypeNotFoundException;
import ru.yourhockey.web.dto.MatcherProductDto;
import ru.yourhockey.web.dto.ProductDto;
import ru.yourhockey.web.mapper.MatcherProductMapper;
import ru.yourhockey.web.mapper.ProductMapper;
import ru.yourhockey.web.preparer.FilterAndPageable;
import ru.yourhockey.web.preparer.Preparer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductApiController implements ProductApi {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final MatcherProductMapper matcherProductMapper;
    private final BrandService brandService;
    private final TypeService typeService;
    private final List<Preparer> preparers;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 3000;

    @CrossOrigin()
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getAllByParams(@RequestParam Map<String, String> requestParams,
                                           @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER) Pageable pageable) {
        FilterAndPageable filterAndPageable = new FilterAndPageable(requestParams, pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Product.class));

        List<Product> products = productService.getAllByParameters(
                filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );
        return products.stream().map(productMapper::map).collect(Collectors.toList());
    }

    @CrossOrigin
    @PostMapping("/product")
    public Long createProduct(@RequestBody Map<String, String> body) {
        Product product = new Product()
                .setModel(body.get("model"))
                .setBrand(brandService.getByShortName(body.get("brandShortName")))
                .setType(typeService.findByShowName(body.get("typeShowName")).orElseThrow(TypeNotFoundException::new))
                .setAge(Age.of(body.get("age")))
                .setDescription(body.get("description"))
                .setSrcImageLink(body.get("srcImageLink"));
        return productService.troubleTicketCreateProduct(product).getProductId();
    }

    @CrossOrigin
    @GetMapping(value = "/allProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MatcherProductDto>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(matcherProductMapper.mapList(products));
    }

    @CrossOrigin
    @GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto getById(@PathVariable long productId) {
        Product product = productService.getById(productId);
        return productMapper.map(product);
    }

    @CrossOrigin
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String searchLine) {
        return ResponseEntity.ok(productMapper.mapList(productService.search(searchLine)));
    }
}
