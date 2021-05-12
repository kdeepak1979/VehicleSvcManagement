package com.innomind.vehiclesvc.mgmt.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.entity.UserRole;


public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> roles;
	private String userName;
	private String password;	
	private boolean isExpired;
	private boolean isLocked;
	private boolean isEnabled;
	
	
	public CustomUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isEnabled = user.isEnabled();
		this.isExpired = user.isExpired();
		this.isLocked = user.isLocked();
		List<UserRole> userRoles = user.getUserRoles();
		
		for(UserRole role : userRoles) {
			System.out.println("CustomUserDetails.CustomUserDetails() role : "+role.getRole().getRoleName());
		}
		roles = user.getUserRoles().stream().map(r -> r.getRole().getRoleName()).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role))
				.collect(Collectors.toList());		
        return authorities;
	}

	@Override
	public String getUsername() {		
		return userName;
	}

	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return !isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return isEnabled;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [roles=" + roles + ", userName=" + userName + ", password=" + password
				+ ", isExpired=" + isExpired + ", isLocked=" + isLocked + ", isEnabled=" + isEnabled + "]";
	}
	
	

}
