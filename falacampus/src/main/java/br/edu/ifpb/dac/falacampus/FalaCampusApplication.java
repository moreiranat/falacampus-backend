package br.edu.ifpb.dac.falacampus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;

@SpringBootApplication
@EnableWebMvc
public class FalaCampusApplication implements WebMvcConfigurer, CommandLineRunner {
	
	private SystemRoleService systemRoleService;
	
	public static void main(String[] args) {
		SpringApplication.run(FalaCampusApplication.class, args);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
	}

	@Override
	public void run(String... args) throws Exception {
		systemRoleService.createDefaultValues();
		
	}
}
