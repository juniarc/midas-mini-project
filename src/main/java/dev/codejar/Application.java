package dev.codejar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "dev.codejar.model.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}





}
