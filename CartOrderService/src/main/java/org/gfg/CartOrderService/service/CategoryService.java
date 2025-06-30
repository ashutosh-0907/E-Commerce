package org.gfg.CartOrderService.service;

import org.gfg.CartOrderService.model.Category;
import org.gfg.CartOrderService.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Optional<Category> findByCategoryName(String categoryName){
        return categoryRepo.findByCategoryName(categoryName);

    }
}
