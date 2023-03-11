package pl.com.great.nysa.it.admin.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdminProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String currency;
    private String imgUrl;
}
