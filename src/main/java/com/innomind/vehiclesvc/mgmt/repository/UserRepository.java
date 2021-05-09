package com.innomind.vehiclesvc.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	public Optional<User> findByUserName(String name);

}
