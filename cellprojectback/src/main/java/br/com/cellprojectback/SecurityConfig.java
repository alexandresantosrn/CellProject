package br.com.cellprojectback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 @Bean
	 protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	    	.csrf(csrf -> csrf.disable())
	    	.cors(cors -> cors.disable())
	        .authorizeHttpRequests(
	            authorizeConfig -> {	
	              authorizeConfig.requestMatchers("/logon/**").permitAll();	     
	              authorizeConfig.requestMatchers(HttpMethod.OPTIONS).permitAll();
	              authorizeConfig.requestMatchers(HttpMethod.POST, "/pessoa/**").permitAll();
	              authorizeConfig.requestMatchers(HttpMethod.POST, "/usuario/**").permitAll();
	              authorizeConfig.anyRequest().authenticated();	              
	            })
	        .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .build();
	  }
}	

