//package com.employee_application.manage_employees.security;
//
////import static org.springframework.security.config.Customizer.withDefaults;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.employee_application.manage_employees.security.filters.JwtAuthenticationEntryPoint;
//import com.employee_application.manage_employees.security.filters.JwtRequestFilter;
//import com.employee_application.manage_employees.security.util.JwtUtil;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurer {
//
//	@Autowired
//	private CustomSuccessHandler customSuccessHandler;
//	
//    private static final String ALLOWED_ORIGIN = "http://localhost:3000";
//
//    @SuppressWarnings("removal")
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
//        http
//            .authorizeHttpRequests()
//                .requestMatchers(
//                		"/h2-console/**", 
//                		"/", 
//                		"/authenticate", 
//                		"/login", 
//                		"/logout", 
//                		"/register", 
//                		"/about/**",
//                		"/auth/**").permitAll()
//                .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                .requestMatchers("/user/**").hasAuthority("USER")
//                .anyRequest().authenticated()
//            .and()
//            .formLogin()
//                .loginPage("/login")
////                .loginProcessingUrl("/authenticate")
//                .successHandler(customSuccessHandler)
////                .permitAll()
//            .and()
//            .logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login")
//            .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .csrf()
//                .disable();
//        
//        http.headers().frameOptions().sameOrigin();
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                    .addMapping("/**")
//                    .allowedMethods("*")
//                    .allowedHeaders("*")
//                    .exposedHeaders("Authorization")
//                    .allowedOrigins(ALLOWED_ORIGIN)
//                    .allowCredentials(true);
//            }
//        };
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN")
//                .build();
//        
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (!userDetailsManager.userExists(admin.getUsername())) {
//            userDetailsManager.createUser(admin);
//        }
//                
//        return userDetailsManager;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) throws Exception {
//        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
//        auth.userDetailsService(userDetailsService(dataSource)).passwordEncoder(bCryptPasswordEncoder);
//        return auth.build();
//    }
//
//    @Bean
//    public JwtRequestFilter jwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
//        return new JwtRequestFilter(userDetailsService, jwtUtil);
//    }
//    
//    @Bean
//	public AccessDeniedHandler accessDeniedHandler() {
//		return new CustomAccessDeniedHandler();
//	}
//	
//	@Bean
//	public AuthenticationEntryPoint entryPoint() {
//		return new JwtAuthenticationEntryPoint();
//	}
//}
