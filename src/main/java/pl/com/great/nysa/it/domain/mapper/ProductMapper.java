package pl.com.great.nysa.it.domain.mapper;

import org.mapstruct.Mapper;
import pl.com.great.nysa.it.domain.Product;
import pl.com.great.nysa.it.domain.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToDto(Product product);
    Product dtoToProduct(ProductDto productDto);
}
