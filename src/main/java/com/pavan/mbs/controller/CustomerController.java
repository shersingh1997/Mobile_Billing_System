package com.pavan.mbs.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.Mobile;
import com.pavan.mbs.entity.ResetPassword;
import com.pavan.mbs.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Mobile Billing System Software";
	}
	@PostMapping("/registerCustomer")
	public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer) {		
		return customerService.addCustomer(customer);		
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Map<String, String>> getCustomer(@PathVariable int id) {
		return customerService.getCustomer(id);				
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Map<String, String>> updateCustomer(@RequestBody ResetPassword request, @PathVariable int id) {
		return customerService.updatePassword(request, id);
	}
	
	@GetMapping("/customer/checkValid/{aadhar}")
	public ResponseEntity<Map<String, String>> validate(@PathVariable String aadhar) {
		return customerService.checkValid(aadhar);
	}
	
	@GetMapping("/customers") 
	public ResponseEntity<Map<String, String>> getCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping("/admin") 
	public ResponseEntity<Map<String, String>> getAdmin() {
		return customerService.getAdmin();
	}
	@GetMapping("/customer/{id}/mobiles")
	public ResponseEntity<Map<String, String>> getMobiles(@PathVariable int id) {		
		return customerService.getMobiles(id);
	}
	@PutMapping("/customer/{cid}/mobiles/changeOperator/{mid}")
	public ResponseEntity<Map<String, String>> updateOperator(@RequestBody Mobile mobile, @PathVariable int cid, @PathVariable int mid) {		
		return customerService.changeOperator(mobile, cid, mid);		
	}
	@PutMapping("/customer/{cid}/mobiles/changeStatus/{mid}")
	public ResponseEntity<Map<String, String>> updateStatus(@RequestBody Mobile mobile, @PathVariable int cid, @PathVariable int mid) {		
		return customerService.changeStatus(mobile, cid, mid);		
	}
	
//	@GetMapping("/customer/op={operator}") 
//	public List<Customer> getbyop(@PathVariable String operator) {
//		return customerService.getbyop(operator);
//	}
}
