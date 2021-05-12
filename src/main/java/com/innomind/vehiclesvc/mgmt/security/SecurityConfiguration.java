package com.innomind.vehiclesvc.mgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {				
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http        
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/dealer/**").hasRole("DEALER_ADMIN")        
        .antMatchers(HttpMethod.POST, "/dealer/**").hasRole("DEALER_ADMIN")
        .antMatchers(HttpMethod.PUT, "/dealer/**").hasRole("DEALER_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/dealer/**").hasRole("DEALER_ADMIN")
        .antMatchers(HttpMethod.GET, "/waranty/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.POST, "/waranty/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.GET, "/vehicle/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.POST, "/vehicle/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.PUT, "/vehicle/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.DELETE, "/vehicle/**").hasAnyRole("ADMIN","USER")                
        .antMatchers(HttpMethod.GET,"/swagger-ui*").permitAll()
        .and().formLogin();
		http.sessionManagement().maximumSessions(1);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	private PasswordEncoder getBcryptEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {				
				return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {				
				return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
			}
		};
	}

}
