package br.edu.ifpb.dac.falacampus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"br.edu.ifpb.dac.ssp.repository"})
public class FalaCampusApplication implements WebMvcConfigurer, CommandLineRunner {
	@Autowired
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
