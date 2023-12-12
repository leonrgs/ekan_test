package br.com.ekan.beneficiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.ekan.beneficiary.repository")
@EntityScan(basePackages = "br.com.ekan.beneficiary.entity")
@EnableWebSecurity
public class EkanBeneficiarySystemApp {

	public static void main(String[] args) {
		SpringApplication.run(EkanBeneficiarySystemApp.class, args);
	}

}
