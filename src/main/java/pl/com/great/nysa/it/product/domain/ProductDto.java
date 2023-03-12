package pl.com.great.nysa.it.product.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.com.great.nysa.it.admin.model.ProductCurrency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    @NotBlank
    @Length(min = 4)
    private String name;

    @NotBlank
    @Length(min = 4)
    private String description;

    @NotBlank
    @Min(0)
    private BigDecimal price;
    @NotBlank
    @Length(min = 4)
    private String category;

    private ProductCurrency currency;

    private String imgUrl;
}
