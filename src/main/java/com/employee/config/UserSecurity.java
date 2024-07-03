package com.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.service.impl.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class UserSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.antMatchers("/token").permitAll()
		.antMatchers("/v1/user/username/*").permitAll()
		.antMatchers("/v1/user/username/token/*").permitAll()
		.antMatchers("/v1/registration").permitAll()
		.antMatchers("/v1/hello").hasAnyRole("USER")
		.antMatchers("/v1/update/user/accountnonexpired").hasAnyRole("ADMIN")
		.antMatchers("/v1/update/user/accountNonLocked").hasAnyRole("ADMIN")
		.antMatchers("/v1/update/user/credentialsNonExpired").hasAnyRole("ADMIN")
		.antMatchers("/v1/update/user/accountEnable").hasAnyRole("ADMIN")
		.antMatchers("/v1/update/user/role").hasAnyRole("ADMIN")
		.antMatchers("/v1/remove/user/role").hasAnyRole("ADMIN")
		.antMatchers("/v1/add/user/role").hasAnyRole("ADMIN")
        .antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(this.passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public AuthenticationManager getBeanAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
}

//   
