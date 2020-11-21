package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.service.ImageService;
import ru.yourhockey.service.logging.MeasurePerformance;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageApiController implements ImageApi {
    private final ImageService imageService;

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE) // try jpg)))
    public ResponseEntity<byte[]> getProductImage(@PathVariable("id") long productId) {
        return ResponseEntity.of(imageService.getProductImage(productId));
    }
}
