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

	@Bean
	CommandLineRunner runner(ProductRepository repository) {
		return args -> {
			Product product = Product.builder()
					.title("Test title")
					.description("Test description")
					.price(BigDecimal.ZERO)
					.category("test category")
					.seller("test seller")
					.build();

			productRepository.save(product);
		};
	}

}
