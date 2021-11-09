package com.pavan.mbs.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.Mobile;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Customer findByEmail(String email);
	List<Customer> findByType(String type);
	Customer findByAadharNumber(String aadhar);
	
//	@Query("select c from Customer c join Mobile m on c.mobile.id=c.id and m.operator=:op")
//	@Query("select c from Customer c inner join fetch Mobile m on c.mobile.id=c.id and m.operator=:op")
//	@Query("from Customer as c inner join fetch c.mobile as m  with m.operator=:op")
//	@Query("select c from Customer c ")
//	List<Customer> findByOperator(@Param("op") String op);
}
