package br.com.ekan.beneficiary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/ekan-console/**"
    };
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
      .csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers(AUTH_WHITE_LIST).permitAll()
      .anyRequest().authenticated()
      .and()
      .httpBasic();
     
     return http.build();
    }
}
