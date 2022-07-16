package br.edu.ifpb.dac.falacampus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;

public class TestApplication implements WebMvcConfigurer, CommandLineRunner{
	
	private SystemRoleService systemRoleService;

	public static void main(String[] args) {

	}
	
	public void addCorsMappings(CorsRegistry registry) {
		
	}

	@Override
	public void run(String... args) throws Exception {
		systemRoleService.createDefaultValues();
	}

}
