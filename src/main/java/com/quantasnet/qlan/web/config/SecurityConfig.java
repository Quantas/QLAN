package com.quantasnet.qlan.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/signup").permitAll()
				.antMatchers("/user/save").permitAll().antMatchers("/denied")
				.permitAll().antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll().antMatchers("/")
				.permitAll().anyRequest().authenticated();

		http.exceptionHandling().accessDeniedPage("/denied");

		http.formLogin().loginPage("/login").permitAll();

		http.logout().logoutUrl("/logout").permitAll();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}
