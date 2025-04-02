package br.com.publico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Serviços Públicos",
			version = "1.0",
			description = "API para gerenciamento de serviços públicos.",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class ApiServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServicosApplication.class, args);
	}

}
