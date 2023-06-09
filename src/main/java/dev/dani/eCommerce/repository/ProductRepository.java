package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.Category;
import dev.dani.eCommerce.model.entity.Gender;
import dev.dani.eCommerce.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String productName);

    Page<Product> findAllByGenderAndAdult(Gender gender, boolean adult,
                                          Pageable pageable);

    Page<Product> findAllByCategory(Category category,
                                    Pageable pageable);

}
