package pl.com.great.nysa.it.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.com.great.nysa.it.admin.model.AdminProduct;
import pl.com.great.nysa.it.admin.service.AdminProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminProductController {

    private final AdminProductService adminProductService;

    @GetMapping("/products")
    public Page<AdminProduct> getProducts(Pageable pageable) {
        return this.adminProductService.getProducts(pageable);
    }

    @PostMapping
    public void create() {
        //return this.adminProductService.create();
    }
}
