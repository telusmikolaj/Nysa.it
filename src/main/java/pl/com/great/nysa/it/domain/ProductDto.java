package pl.com.great.nysa.it.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String seller;
}
