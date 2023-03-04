package pl.com.great.nysa.it.product.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String currency;
    private String imgUrl;
}
