package pl.com.great.nysa.it.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.com.great.nysa.it.admin.model.ProductCurrency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    @Enumerated(EnumType.STRING)
    private ProductCurrency currency;
    private String imgUrl;
    private List<String> imgUrls;

    @CreatedDate
    private LocalDateTime created;


}
