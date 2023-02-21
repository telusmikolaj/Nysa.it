package pl.com.great.nysa.it.product.controller;

import lombok.AllArgsConstructor;
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
public class ProductController {

    private final ProductService productService;

    private final WebScrapper webScrapper;

    @GetMapping()
    public List<ProductDto> getProducts() throws IOException {
        return this.productService.getAllProducts();

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
