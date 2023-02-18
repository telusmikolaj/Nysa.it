package pl.com.great.nysa.it.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.com.great.nysa.it.domain.ProductDto;
import pl.com.great.nysa.it.service.ProductService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final WebScrapper webScrapper;

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @GetMapping("/{title}")
    public ProductDto getByTitle(@PathVariable String title) {
        return this.productService.getByTitle(title);
    }

    @GetMapping
    public void test() throws IOException {
        webScrapper.getProductsFromPage();
    }
}
