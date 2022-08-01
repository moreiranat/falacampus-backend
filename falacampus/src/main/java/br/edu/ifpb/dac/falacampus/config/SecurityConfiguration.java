package br.edu.ifpb.dac.falacampus.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.edu.ifpb.dac.falacampus.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;
import br.edu.ifpb.dac.falacampus.business.service.TokenService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEnconderService passwordEnconderService;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public TokenFilter jwtTokenFilter() {
		return new TokenFilter(tokenService, userService);
	}

	//Configuracoes de Autenticacao (login, acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEnconderService);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {

		List<String> all = Arrays.asList("*");

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedMethods(all);
		corsConfiguration.setAllowedOriginPatterns(all);
		corsConfiguration.setAllowedHeaders(all);
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		CorsFilter corFilter = new CorsFilter(source);

		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(corFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return filter;
	}

	//Confuguracoes de Autorizacao (URL, quem pode acessar cada URL, perfil de acesso)
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//Liberando acesso aos endpoints publicos
		http.csrf().disable().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/api/isTokenValid").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/user")
				.hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				.anyRequest().authenticated() //Essa configuração serve para indicar que outras URLs que não foram configuradas devem ter acesso restrito
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		http.logout(logout -> 
			logout
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.logoutUrl("/api/logout")
			.logoutSuccessHandler(new LogoutSuccessHandler() {

					@Override
					public void onLogoutSuccess(HttpServletRequest request, 
							HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

					}
					
				})
			);
			
	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("12345678")); //hash no formato BCrypt
//	}

}
