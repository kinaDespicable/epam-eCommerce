package dev.dani.eCommerce.service;

import dev.dani.eCommerce.model.dto.request.category.CategoryRequest;
import dev.dani.eCommerce.model.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> fetchAll();

    Page<Category> fetchAllPage(Optional<Integer> page, Optional<Integer> size, Optional<String> sort);


    void create(CategoryRequest categoryRequest);

    boolean exists(String category);

    void delete(Long id);

    boolean existsById(Long id);

    Category fetchByCategoryName(String categoryName);
}
