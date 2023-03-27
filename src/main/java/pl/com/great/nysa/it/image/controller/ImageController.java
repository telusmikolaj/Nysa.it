package pl.com.great.nysa.it.image.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.com.great.nysa.it.image.model.ProductInfo;
import pl.com.great.nysa.it.image.service.S3ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final S3ImageService s3ImageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        try {
            String imageUrl = s3ImageService.uploadImage(fileName, file.getInputStream());
            return "File uploaded successfully. Image URL: " + imageUrl;
        } catch (IOException e) {
            // Handle the exception
            return "Error occurred while uploading the file";
        }
    }
    @PostMapping("/detect")
    public ResponseEntity<List<ProductInfo>> detectProduct(@RequestParam("image") MultipartFile image) throws IOException {
        List<ProductInfo> productInfoList = this.s3ImageService.detectProduct(image);
        return null;
    }
}



