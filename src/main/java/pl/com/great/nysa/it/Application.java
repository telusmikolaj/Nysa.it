package pl.com.great.nysa.it;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.com.great.nysa.it.product.repository.ProductRepository;

@SpringBootApplication
@AllArgsConstructor
public class Application {

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
