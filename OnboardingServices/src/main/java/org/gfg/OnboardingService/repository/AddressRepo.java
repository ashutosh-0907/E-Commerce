package org.gfg.OnboardingService.repository;

import org.gfg.OnboardingService.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
