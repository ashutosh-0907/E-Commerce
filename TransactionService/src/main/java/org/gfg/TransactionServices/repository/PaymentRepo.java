package org.gfg.TransactionServices.repository;

import org.gfg.TransactionServices.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {

    Optional<Payment> findByStripeOrderId(String stripeOrderId);

    Optional<Payment> findByOrderId(Long orderId);

    List<Payment> findByUserId(Long userId);

    List<Payment> findByUserIdOrderByCreatedAtDesc(Long userId);
}
