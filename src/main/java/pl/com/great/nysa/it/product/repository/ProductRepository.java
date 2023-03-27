package pl.com.great.nysa.it.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.com.great.nysa.it.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findProductsByName(String name);


}
