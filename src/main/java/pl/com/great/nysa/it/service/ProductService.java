package pl.com.great.nysa.it.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.great.nysa.it.domain.ProductDto;
import pl.com.great.nysa.it.domain.mapper.ProductMapper;
import pl.com.great.nysa.it.infrastructure.ProductRepository;

import javax.persistence.EntityNotFoundException;

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

    public ProductDto getByTitle(String title) {
        return this.productMapper.productToDto(
                this.productRepository
                .findProductsByTitle(title)
                .orElseThrow(EntityNotFoundException::new));
    }
}
