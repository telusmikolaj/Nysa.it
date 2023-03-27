package pl.com.great.nysa.it.product.controller;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.com.great.nysa.it.webscrapper.api.WebScrapper;
import pl.com.great.nysa.it.product.domain.Product;
import pl.com.great.nysa.it.product.domain.ProductDto;
import pl.com.great.nysa.it.product.service.ProductService;

import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Validated
public class ProductController {

    private final ProductService productService;

    private final WebScrapper webScrapper;

    @GetMapping
    public Page<Product> getProducts(Pageable pageable) {
        return this.productService.getAllProducts(pageable);

    }
    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }




    @DeleteMapping
    public void deleteAll() {
        this.productService.deleteAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable
                                  @Pattern(regexp="[a-z\\d\\-]+")
                                    @Length(max=255)
                                  String id) {

        return this.productService.getById(id);
    }
}
