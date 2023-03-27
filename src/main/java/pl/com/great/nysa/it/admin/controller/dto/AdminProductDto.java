package pl.com.great.nysa.it.admin.controller.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import pl.com.great.nysa.it.admin.model.ProductCurrency;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class AdminProductDto {

    @NotBlank
    @Length(min = 4)
    private String name;

    @NotBlank
    @Length(min = 4)
    private String description;

    @NotNull
    @DecimalMin("1.0")
    private BigDecimal price;

    @NotBlank
    @Length(min = 4)
    private String category;

    private ProductCurrency currency;

    private String imgUrl;

}
