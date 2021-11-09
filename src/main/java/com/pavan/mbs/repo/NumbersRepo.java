package com.pavan.mbs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.mbs.entity.Numbers;

public interface NumbersRepo extends JpaRepository<Numbers, Integer> {

}
