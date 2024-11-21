package com.jackson.helpdesk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jackson.helpdesk.security.JWTAuthenticationFilter;
import com.jackson.helpdesk.security.JWTUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] PUBLIC_MATCHERS = {"/h2console/**"};
	
	@Autowired
    private ConfigurableEnvironment env; // Usando ConfigurableEnvironment
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		http.authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated(); 
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	@Bean
	CorsConfigurationSource corsconfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		
	} 
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
}













