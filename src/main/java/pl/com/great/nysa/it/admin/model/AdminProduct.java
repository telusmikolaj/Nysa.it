package pl.com.great.nysa.it.admin.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "product")
public class AdminProduct {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    @Enumerated(EnumType.STRING)
    private ProductCurrency currency;
    private String imgUrl;
}
