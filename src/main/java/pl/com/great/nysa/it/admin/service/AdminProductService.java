package pl.com.great.nysa.it.admin.service;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.com.great.nysa.it.admin.controller.dto.AdminProductDto;
import pl.com.great.nysa.it.admin.controller.dto.AdminProductMapper;
import pl.com.great.nysa.it.admin.model.AdminProduct;
import pl.com.great.nysa.it.admin.repository.AdminProductRepository;
import pl.com.great.nysa.it.image.service.S3ImageService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    private final S3ImageService imageService;

    private final AdminProductMapper adminProductMapper;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductRepository.findAll(pageable);
    }

    public AdminProduct create(AdminProductDto adminProductDto, List<MultipartFile> files) {
        AdminProduct adminProduct = adminProductMapper.dtoToProduct(adminProductDto);
        adminProduct.setImgUrls(uploadImagesAndReturnUrlList(files));
        adminProduct.setImgUrl(getMainImage(adminProduct.getImgUrls()));
        return adminProductRepository.save(adminProduct);
    }

    private String getMainImage(List<String> imgUrls) {
        if (!imgUrls.isEmpty()) {
            return imgUrls.get(0);
        }
        return "";
    }

    public AdminProduct getProduct(String id) {
        return adminProductRepository.findById(id).orElseThrow();
    }

    public AdminProductDto update(AdminProductDto adminProductDto, String id) {
        AdminProduct updated = adminProductMapper.dtoToProduct(adminProductDto);
        updated.setId(id);
        return adminProductMapper.productToDto(adminProductRepository.save(updated));
    }

    public void delete(String id) {
        this.adminProductRepository.deleteById(id);
    }

    private List<String> uploadImagesAndReturnUrlList(List<MultipartFile> files) {
        return files.stream()
                .map(file -> {
                    try {
                        return imageService.uploadImage(UUID.randomUUID().toString(), file.getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    private String slugify(String input) {
        final Slugify slg = Slugify.builder()
                .customReplacement("_", "-")
                .build();
        return slg.slugify(input);
    }
}
