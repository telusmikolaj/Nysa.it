package pl.com.great.nysa.it.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String seller;

    @CreatedDate
    private LocalDateTime created;


}
