package dev.zoro.productservice.repositories;

import dev.zoro.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductById(UUID id);
}
