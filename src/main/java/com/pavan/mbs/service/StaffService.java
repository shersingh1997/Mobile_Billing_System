package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.repo.CustomerRepo;

@Service
public class StaffService {

	@Autowired
	CustomerRepo customerRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	Map<String, String> body = new HashMap<>();
	
	public ResponseEntity<Map<String, String>> addStaff(Customer customer) {			
		Customer c = customerRepo.save(customer);
		body.put(message, customer.getType() + " Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", c.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public ResponseEntity<Map<String, String>> getStaff(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {
			body.put(message, "Staff details found");
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", c.get().toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);			
		} else {
			body.put(message, "Staff details not found with id: " + id);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<Map<String, String>> getAllStaff() {
		List<Customer> customers = customerRepo.findByType("staff");		
		body.put(message, "List of Staff members");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", customers.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
	
	public ResponseEntity<Map<String, String>> deleteStaff(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {
			customerRepo.deleteById(id);
			body.put(message, "Staff member deleted");
			body.put(status, "true");
			body.put(statusCode, "200");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			body.put(message, "Staff member not found with id: " + id);
			body.put(status, "false");
			body.put(statusCode, "404");			
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
}
