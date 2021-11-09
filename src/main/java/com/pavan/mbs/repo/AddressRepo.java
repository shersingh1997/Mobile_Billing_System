package com.pavan.mbs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.mbs.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
