package com.tehcsage.pvote.config;

import jakarta.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.tehcsage.pvote.filter.CustomLoginFilter;
//import com.tehcsage.pvote.handler.CustomAuthenticationFailureHandler;
//import com.tehcsage.pvote.handler.CustomAuthenticationSuccessHandler;
//import com.tehcsage.pvote.handler.CustomLogoutSuccessHandler;
//
//import com.tehcsage.pvote.util.LoginDatabaseUtil;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tehcsage.pvote.service.CandidateDataService;
import com.tehcsage.pvote.service.CustomUserDetailsService;
import com.tehcsage.pvote.util.DatabaseUtil;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
    private DataSource dataSource;
//	
//	@Autowired
//	private HttpServletRequest request;
	
//    @Autowired
//    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .csrf(csrf -> csrf .disable())
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/checkUser", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/data", "/castVote", "/pages/**").permitAll()
            .anyRequest().authenticated())
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/instructions", true)
            .failureUrl("/login?error=true"))
        .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID"))
        .userDetailsService(userDetailsService);
//        http
//        	.addFilterBefore(new CustomLoginFilter(loginDatabaseUtil()), UsernamePasswordAuthenticationFilter.class)
//        	.csrf()
//        		.and()
//            .authorizeRequests(auth -> auth
//            	.antMatchers("/login", "/logout", "/images/**", "/static/**").permitAll()
//                .anyRequest().authenticated())
//            .formLogin()
//                .loginPage("/login")
//                .successHandler(authenticationSuccessHandler())
//                .failureHandler(authenticationFailureHandler())
//                .permitAll()
//                .and()
//            .logout()
//            	.logoutUrl("/logout")
//            	.logoutSuccessHandler(customLogoutSuccessHandler)
//                .permitAll()
//                .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//		        .invalidSessionUrl("/login") // Redirect to login page if session expires
//		        .maximumSessions(1) // Only one session per user
//		        .and()
//		        .sessionFixation().migrateSession(); // Prevent session fixation attacks
        
        return http.build();
    }
    
    
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Password encoder for storing passwords securely
//    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // No-op encoder for plain text passwords
//    }
    
    @Bean
    public DatabaseUtil loginDatabaseUtil() {
        return new DatabaseUtil(dataSource);
    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new CustomAuthenticationSuccessHandler(loginDatabaseUtil());
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler(loginDatabaseUtil());
//    }
//    
//    @Bean
//    public LoginDatabaseUtil loginDatabaseUtil(HttpServletRequest request, DataSource dataSource) {
//        return new LoginDatabaseUtil(request, dataSource);
//    }
}