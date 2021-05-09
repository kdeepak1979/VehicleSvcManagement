package com.innomind.vehiclesvc.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.UserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
