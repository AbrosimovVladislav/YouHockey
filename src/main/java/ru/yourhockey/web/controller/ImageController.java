package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.service.ImageService;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE) // try jpg)))
    public ResponseEntity<byte[]> getProductImage(@PathVariable("id") long productId) {
        return ResponseEntity.of(imageService.getProductImage(productId));
    }
}
