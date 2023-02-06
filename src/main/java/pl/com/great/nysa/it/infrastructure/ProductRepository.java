package pl.com.great.nysa.it.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.com.great.nysa.it.domain.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    Optional<Product> findProductsByTitle(String title);
}
