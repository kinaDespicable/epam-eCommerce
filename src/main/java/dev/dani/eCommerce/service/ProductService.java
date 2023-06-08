package dev.dani.eCommerce.service;

import dev.dani.eCommerce.model.dto.product.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService  {
    Page<ProductResponse> fetchAll(Optional<String> gender, Optional<Boolean> isAdult);
}
