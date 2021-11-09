package com.pavan.mbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.pavan.mbs.controller", "com.pavan.mbs.service"})
@EntityScan("com.pavan.mbs.entity")
@EnableJpaRepositories("com.pavan.mbs.repo")
public class MobileBillingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileBillingSystemApplication.class, args);
	}

}
