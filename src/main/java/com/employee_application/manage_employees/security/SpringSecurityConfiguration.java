 package com.employee_application.manage_employees.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringSecurityConfiguration {
	
	private static final String ROLE_USER = "USER";
	private static final String ROLE_ADMIN = "USER";
    private static final String ALLOWED_ORIGIN = "http://localhost:3000";

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		http.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults()); 
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		return http.build();
	}
	
	// Allowing Resources from cross origins
	
	@SuppressWarnings("unused")
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			public void addCorsMapping(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowedOrigins(ALLOWED_ORIGIN)
				.allowCredentials(true);
			}
		};
	}
	 
	// Hashing the user name and password of the users:
	
	@Bean
	public UserDetailsService userDeailsService() {
		var user = User
				.withUsername("ragul")
				.password("password")
				.passwordEncoder(str -> MyPasswordEncoder().encode(str))
				.roles(ROLE_USER)
				.build();
		
		var admin = User
				.withUsername("admin")
				.password("password")
				.passwordEncoder(str -> MyPasswordEncoder().encode(str))
				.roles(ROLE_ADMIN)
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// Creating a new Bean for hashing the password:
	
	@Bean 
	public BCryptPasswordEncoder MyPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


