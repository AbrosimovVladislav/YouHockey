package ru.yourhockey.service.initialProductScrapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.ProductRepo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Deprecated
@Component
@RequiredArgsConstructor
public class ImageLoader {
    private final ProductRepo productRepo;
    @Value("${product.photo.dir}")
    private String imgDir;

    /**
     * Load and save/update all images from product.imageLink
     * Saving goes in format product.brand_product.model_product.age.png
     * return List of images names that was loaded
     */
    public List<String> loadImages() {
        List<Product> products = productRepo.findAll();
        List<String> loadedImgLinks = new ArrayList<>();
        // TODO: проход и скачивание по тем, у кого есть срцИмгЛинк, но нет имгЛинк
        for (Product product : products) {
            String type = product.getType().getShowName().replaceAll("/","-");
            String imgName = product.getBrand().getShortName() + "_" + product.getModel() + ".png";
            try {
                var url = new URL("http://" + "opt-1274418.ssl.1c-bitrix-cdn.ru" + product.getSrcImageLink());
                String imgDirName = System.getProperty("user.home") + "/" + imgDir;
                if (Files.notExists(Paths.get(imgDirName)))
                    Files.createDirectory(Paths.get(imgDirName)); // redundant perhaps
                if (Files.notExists(Paths.get(imgDirName, type))) Files.createDirectory(Paths.get(imgDirName, type));
                String imgPath = imgDirName + "/" + type + "/" + imgName;
                FileCopyUtils.copy(url.openStream(), new FileOutputStream(imgPath));
                product.setImageLink(imgPath);
                loadedImgLinks.add(imgPath);
            } catch (IOException e) {
                log.error("Image downloading failed [srcImageLink={}; {}]", product.getSrcImageLink(), e.getMessage());
            }
        }
        productRepo.saveAll(products);
        return loadedImgLinks;
    }
}
