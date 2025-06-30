package org.gfg.CartOrderService.repository;

import org.gfg.CartOrderService.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
    Optional<Cart> findByUserId(int userId);
}
