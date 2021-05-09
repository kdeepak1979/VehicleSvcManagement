package com.innomind.vehiclesvc.mgmt.security;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innomind.vehiclesvc.mgmt.entity.User;
import com.innomind.vehiclesvc.mgmt.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("CustomUserDetailService.loadUserByUsername() userId = "+userId);
		/*List<User> users = userRepository.findAll();
		System.out.println("CustomUserDetailService.loadUserByUsername() count : "+users.size());
		for(User user : users ) {
			System.out.println("CustomUserDetailService.loadUserByUsername() name : "+user.getUserName() +" role : "+user.getUserRoles());
		}*/
		Optional<User> user = userRepository.findByUserName(userId);
		//System.out.println("CustomUserDetailService.loadUserByUsername() credentials = "+user.get());
		user.orElseThrow(() -> new UsernameNotFoundException("User with Id '" + userId + "' not found"));
		return user.map(CustomUserDetails::new).get();
		/*(if(true) {
			throw new UsernameNotFoundException("User with Id '" + userId + "' not found");
		}
		return null;*/
	}

}
