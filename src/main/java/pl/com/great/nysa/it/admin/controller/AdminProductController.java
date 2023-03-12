package pl.com.great.nysa.it.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.com.great.nysa.it.admin.controller.dto.AdminProductDto;
import pl.com.great.nysa.it.admin.model.AdminProduct;
import pl.com.great.nysa.it.admin.service.AdminProductService;

import javax.validation.Valid;

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

    @GetMapping("/products/{id}")
    public AdminProduct getProduct(@PathVariable String id) {
        return this.adminProductService.getProduct(id);
    }

    @PostMapping("/products")
    public AdminProduct create(@RequestBody @Valid AdminProductDto adminProductDto) {
        return this.adminProductService.create(adminProductDto);
    }

    @PutMapping("/products/{id}")
    public AdminProductDto update(@RequestBody @Valid AdminProductDto adminProductDto, @PathVariable String id) {

        return this.adminProductService.update(adminProductDto, id);
    }

}
