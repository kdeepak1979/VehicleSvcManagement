package com.innomind.vehiclesvc.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innomind.vehiclesvc.mgmt.entity.DealerDetail;
import com.innomind.vehiclesvc.mgmt.entity.User;

@Repository
public interface DealerDetailRepository extends JpaRepository<DealerDetail, Integer>{

	public Optional<DealerDetail> findByDealer(User dealer);
}
