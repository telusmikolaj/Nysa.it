package pl.com.great.nysa.it.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.great.nysa.it.admin.controller.dto.AdminProductDto;
import pl.com.great.nysa.it.admin.controller.dto.AdminProductMapper;
import pl.com.great.nysa.it.admin.model.AdminProduct;
import pl.com.great.nysa.it.admin.repository.AdminProductRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    private final AdminProductMapper adminProductMapper;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductRepository.findAll(pageable);
    }

    public AdminProduct create(AdminProductDto adminProductDto) {

        return adminProductRepository.save(adminProductMapper.dtoToProduct(adminProductDto));
    }

    public AdminProduct getProduct(String id) {
        return adminProductRepository.findById(id).orElseThrow();
    }

    public AdminProductDto update(AdminProductDto adminProductDto, String id) {
        AdminProduct updated = adminProductMapper.dtoToProduct(adminProductDto);
        updated.setId(id);
        return adminProductMapper.productToDto(adminProductRepository.save(updated));
    }
}
