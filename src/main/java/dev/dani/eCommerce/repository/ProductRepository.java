package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String productName);


}