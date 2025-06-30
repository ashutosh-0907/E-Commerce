package org.gfg.CartOrderService.repository;

import org.gfg.CartOrderService.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    Optional<Category> findByCategoryName(String categoryName);

}
