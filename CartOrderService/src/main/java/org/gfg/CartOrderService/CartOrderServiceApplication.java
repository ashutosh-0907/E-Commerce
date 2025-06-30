package org.gfg.CartOrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"org.gfg.CartOrderService.repository",
		"org.gfg.OnboardingService.repository" // <-- add this
})
@EntityScan(basePackages = {
		"org.gfg.CartOrderService.model",
		"org.gfg.OnboardingService.model" // <-- add this
})
public class CartOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartOrderServiceApplication.class, args);
	}

}
