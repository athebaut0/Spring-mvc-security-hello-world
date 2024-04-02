package com.openclassrooms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf() //
				.disable() //
				.authorizeRequests() //
				.antMatchers("/admin/**") //
				.hasRole("ADMIN") //
				.antMatchers("/anonymous*") //
				.anonymous() //
				.antMatchers("/login*") //
				.permitAll() //
				.anyRequest() //
				.authenticated() //
				.and() //
				.formLogin() //
				.loginPage("/login.html") //
				.loginProcessingUrl("/perform_login") //
				.defaultSuccessUrl("/homepage.html", true) //
				// .failureUrl("/login.html?error=true")
//	            .failureHandler(authenticationFailureHandler())
				.and() //
				.logout() //
				.logoutUrl("/perform_logout") //
				.deleteCookies("JSESSIONID");
//	            .logoutSuccessHandler(logoutSuccessHandler());
		// .and()
		// .exceptionHandling().accessDeniedPage("/accessDenied");
		// .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		return http.build();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

}