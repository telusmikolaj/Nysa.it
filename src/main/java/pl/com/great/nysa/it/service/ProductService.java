package pl.com.great.nysa.it.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.great.nysa.it.domain.Product;
import pl.com.great.nysa.it.domain.ProductDto;
import pl.com.great.nysa.it.domain.mapper.ProductMapper;
import pl.com.great.nysa.it.infrastructure.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;
    public ProductDto create(ProductDto productDto) {
        return this.productMapper.productToDto(
                this.productRepository.save(
                        this.productMapper.dtoToProduct(productDto)));
    }

    public ProductDto getByTitle(String name) {
        return this.productMapper.productToDto(
                this.productRepository
                .findProductsByName(name)
                .orElseThrow(EntityNotFoundException::new));
    }

    public List<ProductDto> saveProductList(List<Product> productList) {
        return this.productMapper.mapToDtoList(this.productRepository.saveAll(productList));
    }

    public List<ProductDto> getAllProducts() {
        return this.productMapper.mapToDtoList(this.productRepository.findAll());
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }


}
