package pl.com.great.nysa.it.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String currency;
    private String imgUrl;


    @CreatedDate
    private LocalDateTime created;


}
