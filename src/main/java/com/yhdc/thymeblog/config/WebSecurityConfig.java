package com.yhdc.thymeblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		// authentication manager (see below)
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/anonymous*")
				.anonymous().antMatchers("/login*").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/auth/login.html").loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/index.html", true).failureUrl("/login.html?error=true");
//	      .failureHandler(authenticationFailureHandler())
//	      .and()
//	      .logout()
//	      .logoutUrl("/auth/logout")
//	      .deleteCookies("JSESSIONID")
//	      .logoutSuccessHandler(logoutSuccessHandler());

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
