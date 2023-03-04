package pl.com.great.nysa.it.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.great.nysa.it.admin.model.AdminProduct;
import pl.com.great.nysa.it.admin.repository.AdminProductRepository;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductRepository.findAll(pageable);
    }

    public void create() {
        AdminProduct adminProduct = new AdminProduct();
        adminProduct.setName("test");
        adminProductRepository.save(adminProduct);
    }
}
