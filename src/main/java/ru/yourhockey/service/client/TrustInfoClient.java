//package ru.yourhockey.service.client;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import ru.yourhockey.model.product.Product;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class TrustInfoClient {
//
//    public static final String TRUST_INFO_BASE_PATH = "http://localhost:8086";
//    public static final String PRODUCT_CREATE_TRUST_INFO_PATH = "/product";
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public Product saveToTrustInfo(Product product) {
//        String url = TRUST_INFO_BASE_PATH + PRODUCT_CREATE_TRUST_INFO_PATH;
//        return restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                new HttpEntity<>(product),
//                new ParameterizedTypeReference<Product>() {
//                }
//        ).getBody();
//    }
//}
