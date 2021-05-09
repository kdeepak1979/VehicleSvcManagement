package com.innomind.vehiclesvc.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
