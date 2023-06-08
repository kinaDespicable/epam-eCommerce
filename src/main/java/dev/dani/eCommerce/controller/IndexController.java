package dev.dani.eCommerce.controller;

import dev.dani.eCommerce.model.entity.Category;
import dev.dani.eCommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static dev.dani.eCommerce.config.ApplicationConstants.APPLICATION_NAME;
import static dev.dani.eCommerce.config.ApplicationConstants.PAGE_TITLE;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Category> categories = categoryService.fetchAll();

        model.addAttribute(PAGE_TITLE.getValue(), APPLICATION_NAME.getValue());
        model.addAttribute("categories", categories);

        return "index";
    }

}
