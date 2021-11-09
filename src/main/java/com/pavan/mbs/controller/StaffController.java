package com.pavan.mbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.service.CustomerService;
import com.pavan.mbs.service.StaffService;

@RestController
public class StaffController {

	@Autowired
	private StaffService staffService;

	@PostMapping("/registerStaff")
	public ResponseEntity<Map<String, String>> addStaff(@RequestBody Customer customer) {		
		return staffService.addStaff(customer);		
	}
	@GetMapping("/staff/{id}")
	public ResponseEntity<Map<String, String>> getStaff(@PathVariable int id) {
		return staffService.getStaff(id);				
	}
	@GetMapping("/staff")
	public ResponseEntity<Map<String, String>> getCustomer() {
		return staffService.getAllStaff();				
	}
	@DeleteMapping("staff/{id}")
	public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable int id) {
		return staffService.deleteStaff(id);
	}
}
