package pl.com.great.nysa.it.admin.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String currency;
    private String imgUrl;
}
