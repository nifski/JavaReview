package com.PharmVault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.PharmVault.entity")
@EnableJpaRepositories(basePackages = "com.PharmVault.repo")
@ComponentScan(basePackages = "com.PharmVault")
public class PharmVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmVaultApplication.class, args);
	}

}
