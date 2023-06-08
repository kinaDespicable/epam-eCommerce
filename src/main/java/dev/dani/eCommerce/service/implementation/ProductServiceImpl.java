package dev.dani.eCommerce.service.implementation;

import dev.dani.eCommerce.model.dto.product.ProductResponse;
import dev.dani.eCommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<ProductResponse> fetchAll(Optional<String> gender, Optional<Boolean> isAdult) {
        return null;
    }
}
