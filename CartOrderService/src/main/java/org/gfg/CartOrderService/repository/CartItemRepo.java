package org.gfg.CartOrderService.repository;

import org.gfg.CartOrderService.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Integer> {

    Optional<CartItem> findByUserIdAndProduct_ProductId(int userId, int productId);
}
