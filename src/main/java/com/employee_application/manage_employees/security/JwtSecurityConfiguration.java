//package com.employee_application.manage_employees.security;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.interfaces.RSAPublicKey;
//import java.util.UUID;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class JwtSecurityConfiguration {
//	
//	private static final String ALLOWED_ORIGIN = "http://localhost:3000";
//
//	@SuppressWarnings("removal")
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(
//				auth -> auth
//							.requestMatchers("/h2-console/**").permitAll()
//							.anyRequest().authenticated());
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults()); 
//		http.csrf().disable();
//		http.headers().frameOptions().sameOrigin();
//		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//		return http.build();
//	}
//	
//	// Allowing Resources from cross origins
//	@SuppressWarnings("unused")
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			public void addCorsMapping(CorsRegistry registry) {
//				registry
//					.addMapping("/**")
//					.allowedMethods("*")
//					.allowedHeaders("*")
//					.allowedOrigins(ALLOWED_ORIGIN)
//					.allowCredentials(true);
//			}
//		};
//	}
//	 
//	// Hashing the user name and password of the users
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {		
//		
//		UserDetails admin = User
//				.withUsername("admin")
//				.password("password")
//				.passwordEncoder(str -> passwordEncoder().encode(str))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user1 = User
//				.withUsername("user")
//				.password("password")
//				.passwordEncoder(str -> passwordEncoder().encode(str))
//				.roles("USER")
//				.build();
//		
//		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//		userDetailsManager.createUser(admin);
//		userDetailsManager.createUser(user1); 
//		return userDetailsManager;
////		return new InMemoryUserDetailsManager(admin, user1);  
//	} 
//	
//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//				.build();
//	}
//	
//	// Creating a new Bean for hashing the password
//	
//	@Bean 
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	// Step - 1 : Create the key pair
//	
//	@Bean KeyPair keyPair() throws NoSuchAlgorithmException {
//		var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//		keyPairGenerator.initialize(2048);
//		return keyPairGenerator.generateKeyPair(); 
//	} 
//	
//
//	// Step - 2 : Create RSA key object using key pair:
//	
//	@Bean 
//	public RSAKey rsaKey(KeyPair keyPair) {
//		return new RSAKey
//					.Builder((RSAPublicKey)keyPair.getPublic())
//					.privateKey(keyPair.getPrivate())
//					.keyID(UUID.randomUUID().toString())
//					.build();
//	} 
//	 
//	@Bean 
//	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
//		var jwtSet = new JWKSet(rsaKey);  
//		return (jwkSelector, context) -> jwkSelector.select(jwtSet);
//
//	}
//	
//	@Bean
//	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
//		return NimbusJwtDecoder
//				.withPublicKey(rsaKey.toRSAPublicKey())
//				.build();
//	}
//	
//	@Bean JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//		return new NimbusJwtEncoder(jwkSource);
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
