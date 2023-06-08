package dev.dani.eCommerce.model.dto.request.category;

import dev.dani.eCommerce.model.dto.request.CreateRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest implements CreateRequest {

    @NotEmpty(message = "Category cannot be empty")
    private String category;
    private String description;

}
