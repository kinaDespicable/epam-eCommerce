package dev.dani.eCommerce.service.implementation;

import dev.dani.eCommerce.exception.ResourceAlreadyExistException;
import dev.dani.eCommerce.exception.ResourceNotFoundException;
import dev.dani.eCommerce.model.dto.request.Validatable;
import dev.dani.eCommerce.model.dto.request.product.ProductRequest;
import dev.dani.eCommerce.model.entity.Category;
import dev.dani.eCommerce.model.entity.Gender;
import dev.dani.eCommerce.model.entity.Product;
import dev.dani.eCommerce.repository.ProductRepository;
import dev.dani.eCommerce.service.CategoryService;
import dev.dani.eCommerce.service.GenderService;
import dev.dani.eCommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.dani.eCommerce.service.Page.getPageable;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService, Validatable<ProductRequest> {

    private static final String DEFAULT_GENDER = "Male";

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final GenderService genderService;


    @Override
    public Page<Product> fetchAll(Boolean adult,
                                  Optional<String> gender,
                                  Optional<Integer> page,
                                  Optional<Integer> size,
                                  Optional<String> sort) {

        String _gender = gender.orElse(DEFAULT_GENDER);
        Pageable pageable = getPageable(page, Optional.of(2), sort);

        Gender genderEntity = genderService.fetchGender(_gender);

        return productRepository.findAllByGenderAndAdult(genderEntity, adult, pageable);
    }

    @Override
    public void create(ProductRequest productRequest) {
        checkExistenceForCreation(productRequest);

        Category category = categoryService.fetchByCategoryName(productRequest.getCategory().trim());
        Gender gender = genderService.fetchGender(productRequest.getGender().trim());

        Product product = Product.builder()
                .name(productRequest.getProductName().trim())
                .description(productRequest.getDescription().trim())
                .category(category)
                .image(productRequest.getImage())
                .gender(gender)
                .adult(productRequest.isAdult())
                .quantity(productRequest.getQuantity())
                .unitPrice(productRequest.getUnitPrice())
                .build();

        productRepository.save(product);

    }

    @Override
    public boolean existsByProductName(String productName) {
        return productRepository.existsByName(productName.trim());
    }

    @Override
    public Page<Product> fetchAllByCategory(String category,
                                            Optional<Integer> page,
                                            Optional<Integer> size,
                                            Optional<String> sort) {

        Category fetchedCategory = categoryService.fetchByCategoryName(category.trim());
        Pageable pageable = getPageable(page, Optional.of(2), sort);

        return productRepository.findAllByCategory(fetchedCategory, pageable);
    }

    @Override
    public Product fetchById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found."));
    }

    @Override
    public void checkExistenceForCreation(ProductRequest request) throws ResourceAlreadyExistException {
        if (productRepository.existsByName(request.getProductName().trim())) {
            throw new ResourceAlreadyExistException("Product already exist.");
        }
    }
}
