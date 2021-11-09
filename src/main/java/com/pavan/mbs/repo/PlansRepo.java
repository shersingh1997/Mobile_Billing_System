package com.pavan.mbs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.mbs.entity.Plans;

public interface PlansRepo extends JpaRepository<Plans, Integer> {
	
	List<Plans> findByOperator(String operator);
}
