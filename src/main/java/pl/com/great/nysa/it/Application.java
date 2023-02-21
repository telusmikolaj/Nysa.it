package pl.com.great.nysa.it;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.great.nysa.it.domain.Product;
import pl.com.great.nysa.it.infrastructure.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
@AllArgsConstructor
public class Application {

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
