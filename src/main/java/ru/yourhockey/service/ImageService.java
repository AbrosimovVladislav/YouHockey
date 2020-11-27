package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.ProductRepo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepo productRepo;
    @Value("${product.photo.dir}")
    private String imgDir;

    public void updateImageLink(Product product) {
        String srcImageLink = product.getSrcImageLink();
        if (srcImageLink != null && product.getType() != null) {
            String imgDirName = System.getProperty("user.home") + "/" + imgDir;
            String imgName = product.getBrand().getShortName() + "_" + product.getModel() + "_" + product.getAge() + ".png";
            //ToDo убрать работу с типами через передачу типа в метод
            product.setImageLink(downloadImage(srcImageLink, imgDirName, imgName, product.getType()));
        }
    }

    public String downloadImage(String srcImageLink, String imgDirName, String imgName, Type type) {
        //ToDo убрать работу с типами через передачу типа в метод
        String typeLine = type.getShowName().replaceAll("/", "-");

        String resultImagePath = "";
        try {
            URL url = new URL(srcImageLink);
            if (Files.notExists(Paths.get(imgDirName)))
                Files.createDirectory(Paths.get(imgDirName)); // redundant perhaps
            if (Files.notExists(Paths.get(imgDirName, typeLine)))
                Files.createDirectory(Paths.get(imgDirName, typeLine));

            resultImagePath = imgDirName + "/" + typeLine + "/" + imgName;
            FileCopyUtils.copy(url.openStream(), new FileOutputStream(resultImagePath));
        } catch (IOException e) {
            log.error("Downloading of {} image are rejected!!!. Exception: {}", srcImageLink, e.getMessage());
        }

        if (resultImagePath.equals("")) {
            throw new RuntimeException("There is no possibility to download image " + srcImageLink);
        }
        return resultImagePath;
    }

    public Optional<byte[]> getProductImage(long productId) {
        return productRepo.findById(productId)
                .map(Product::getImageLink)
                .map(Paths::get)
                .map(p -> {
                    try {
                        return Files.readAllBytes(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }
}
