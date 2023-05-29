package com.tropical.flowers.tropicalflowers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration {

  @Autowired
  JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  CustomUserDetailsService CustomUserDetailsService;

  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(11);
  }

  @Bean
  DaoAuthenticationProvider authProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(CustomUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
               .authenticationProvider(authProvider())
               .build();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(withDefaults()).csrf(csrf -> csrf.disable())
      .exceptionHandling(withDefaults())
      .sessionManagement(management -> management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/user", "/user/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMINISTRADOR")
            .requestMatchers(HttpMethod.GET, "/product").permitAll()
            .requestMatchers(HttpMethod.GET, "/product/**").authenticated()
            .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMINISTRADOR")
            .requestMatchers(HttpMethod.DELETE, "/product/**").hasAnyRole("ADMINISTRADOR")
            .anyRequest().denyAll()
          ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      
    return http.build();
  }
}
