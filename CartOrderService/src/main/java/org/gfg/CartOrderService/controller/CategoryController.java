package org.gfg.CartOrderService.controller;

import org.gfg.CartOrderService.model.Category;
import org.gfg.CartOrderService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/category-service")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/find/category/{categoryName}")
    public Optional<Category> findByCategoryName(@PathVariable String categoryName){
        return categoryService.findByCategoryName(categoryName);

    }
}
