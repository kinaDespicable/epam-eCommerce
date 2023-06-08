package dev.dani.eCommerce.controller;


import dev.dani.eCommerce.model.dto.product.ProductResponse;
import dev.dani.eCommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static dev.dani.eCommerce.config.ApplicationConstants.PAGE_TITLE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String productsPage(@RequestParam("gender") Optional<String> gender,
                               @RequestParam("is_adult") Optional<Boolean> isAdult,
                               Model model) {

        Page<ProductResponse> products = productService.fetchAll(gender, isAdult);
        model.addAttribute("products", products);
        model.addAttribute(PAGE_TITLE.getValue(), "Products");
        return "product";
    }


}
