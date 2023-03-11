package pl.com.great.nysa.it.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.com.great.nysa.it.admin.model.AdminProduct;

import java.util.UUID;

public interface AdminProductRepository extends MongoRepository<AdminProduct, String> {
}
