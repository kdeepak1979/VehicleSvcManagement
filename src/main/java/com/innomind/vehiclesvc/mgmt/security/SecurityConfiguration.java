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




//@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		System.out.println("SecurityConfiguration.configure()==============> "+auth);
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("SecurityConfiguration.configure()============> "+http);		
		http        
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/dealer/**").hasAnyRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.POST, "/dealer/**").hasAnyRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/dealer/**").hasAnyRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/dealer/**").hasAnyRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/waranty/**").hasAnyRole("ADMIN","USER")
        .antMatchers(HttpMethod.POST, "/waranty/**").hasAnyRole("ADMIN","USER")
        .and().formLogin();
		//only one session will be active for specific user
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
				System.out.println(String.format("############# SecurityConfiguration.getBcryptEncoder().new PasswordEncoder() {...}.encode() charSequence = %s",rawPassword.toString()));
				return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				System.out.println(String.format("############## SecurityConfiguration.getBcryptEncoder().new PasswordEncoder() {...}.matches() charSequence = %s, s = %s",rawPassword,encodedPassword));				
				return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
			}
		};
	}

}
