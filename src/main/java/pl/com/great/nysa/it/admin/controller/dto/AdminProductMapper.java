package pl.com.great.nysa.it.admin.controller.dto;

import org.mapstruct.Mapper;
import pl.com.great.nysa.it.admin.model.AdminProduct;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminProductMapper {

    AdminProductDto productToDto(AdminProduct product);
    AdminProduct dtoToProduct(AdminProductDto productDto);

    List<AdminProductDto> mapToDtoList(List<AdminProduct> products);
}
