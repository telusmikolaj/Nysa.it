package pl.com.great.nysa.it.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.com.great.nysa.it.product.domain.Product;
import pl.com.great.nysa.it.product.domain.ProductDto;
import pl.com.great.nysa.it.product.domain.mapper.ProductMapper;
import pl.com.great.nysa.it.product.repository.ProductRepository;

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

    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }


    public ProductDto getById(String id) {
        return this.productMapper.productToDto(
                this.productRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new));
    }
}
