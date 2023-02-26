package pl.com.great.nysa.it.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.com.great.nysa.it.api.WebScrapper;
import pl.com.great.nysa.it.domain.Product;
import pl.com.great.nysa.it.domain.ProductDto;
import pl.com.great.nysa.it.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{title}")
    public ProductDto getByTitle(@PathVariable String title) {
        return this.productService.getByTitle(title);
    }

    @GetMapping("/scrap")
    public List<ProductDto> test() throws IOException {
        List<Product> productsFromPage = webScrapper.getProductsFromPage();
        return this.productService.saveProductList(productsFromPage);
    }

    @DeleteMapping
    public void deleteAll() {
        this.productService.deleteAll();
    }
}
