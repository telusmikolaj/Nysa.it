package pl.com.great.nysa.it.product.domain.mapper;

import org.mapstruct.Mapper;
import pl.com.great.nysa.it.product.domain.Product;
import pl.com.great.nysa.it.product.domain.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToDto(Product product);
    Product dtoToProduct(ProductDto productDto);

    List<ProductDto> mapToDtoList(List<Product> products);


}
