package org.gfg.OnboardingService.repository;

import org.gfg.OnboardingService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByMobNo(String mobNo);
    Optional<User> findById(int id);
}
