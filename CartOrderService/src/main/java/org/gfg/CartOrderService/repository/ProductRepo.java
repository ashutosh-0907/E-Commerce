package org.gfg.CartOrderService.repository;

import org.gfg.CartOrderService.model.Category;
import org.gfg.CartOrderService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByProductId(int productId);
}
