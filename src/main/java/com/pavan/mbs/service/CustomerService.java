package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.Mobile;
import com.pavan.mbs.entity.ResetPassword;
import com.pavan.mbs.repo.AddressRepo;
import com.pavan.mbs.repo.CustomerRepo;
import com.pavan.mbs.repo.MobileRepo;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	MobileRepo mobileRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	Map<String, String> body = new HashMap<>();
	
	public ResponseEntity<Map<String, String>> addCustomer(Customer customer) {			
		Customer c = customerRepo.save(customer);
		body.put(message, customer.getType() + " Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", c.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public ResponseEntity<Map<String, String>> checkValid(String aadhar) {
		Customer c = customerRepo.findByAadharNumber(aadhar);
		if(c != null) {
			body.put(message, "Customer has registered with Aadhar: " + aadhar);
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", c.toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);
		} else {
			body.put(message, "Customer not found with Aadhar: " + aadhar);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<Map<String, String>> getCustomer(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {
			body.put(message, "Customer details found");
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", c.get().toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);			
		} else {
			body.put(message, "Customer details not found with id: " + id);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<Map<String, String>> getCustomers() {
		List<Customer> customers = customerRepo.findByType("customer");		
		body.put(message, "List of customers");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", customers.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
	public ResponseEntity<Map<String, String>> updatePassword(ResetPassword request, int cid) {
		body = new HashMap<>();
		Customer c = customerRepo.getById(cid);
		if(c.getPassword().equals(request.getExistingPassword())) {
			if(request.getNewPassword().equals(request.getConfirmNewPassword())) {
				c.setPassword(request.getNewPassword());
				customerRepo.save(c);
				body.put(message, "Password changed successfully");
				body.put(status, "true");
				body.put(statusCode, "200");		
				return new ResponseEntity<>(body, HttpStatus.OK);
			} else {
				body.put("error", "New password and Confirm password are not equal!");
				body.put(status, "false");
				body.put(statusCode, "403");		
				return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
			}
		} else {
			body.put("error", "Current password is incorrect!");
			body.put(status, "false");
			body.put(statusCode, "403");		
			return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
		}
	}
	
	public ResponseEntity<Map<String, String>> getAdmin() {
		Customer admin = customerRepo.findByType("admin").get(0);
		body.put(message, "Admin Details");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", admin.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
	public ResponseEntity<Map<String, String>> getMobiles(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {
			List<Mobile> mobiles = c.get().getMobiles();			
			body.put(message, "Customer mobile numbers");
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", mobiles.toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);
		} else {
			body.put(message, "Customer not found with id: " + id);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<Map<String, String>> changeOperator(Mobile mobile, int cid, int mid) {
		Optional<Customer> c = customerRepo.findById(cid);
		if(c.isPresent()) {
			Customer cust = c.get();
			Mobile mobi;
			List<Mobile> m = cust.getMobiles().stream().filter(mob -> mob.getId()==mid).collect(Collectors.toList());
			if(!m.isEmpty()) {
				mobi = m.get(0);
				mobi.setOperator(mobile.getOperator());
				mobi = mobileRepo.save(mobi);
				body.put(message, "Operator updated Successfully");
				body.put(status, "true");
				body.put(statusCode, "200");
				body.put("data", mobi.toString());
				return new ResponseEntity<>(body, HttpStatus.OK);
			}	else {
				body.put(message, "Mobile not found with id: " + mid);
				body.put(status, "false");
				body.put(statusCode, "404");
				body.put("data", null);
				return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			}
		} else {
			body.put(message, "Customer not found with id: " + cid);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Map<String, String>> changeStatus(Mobile mobile, int cid, int mid) {
		Optional<Customer> c = customerRepo.findById(cid);
		if(c.isPresent()) {
			Customer cust = c.get();
			Mobile mobi;
			List<Mobile> m = cust.getMobiles().stream().filter(mob -> mob.getId()==mid).collect(Collectors.toList());
			if(!m.isEmpty()) {
				mobi = m.get(0);
				mobi.setActive(mobile.isActive());
				mobi = mobileRepo.save(mobi);
				body.put(message, "Status updated Successfully");
				body.put(status, "true");
				body.put(statusCode, "200");
				body.put("data", mobi.toString());
				return new ResponseEntity<>(body, HttpStatus.OK);
			}	else {
				body.put(message, "Mobile not found with id: " + mid);
				body.put(status, "false");
				body.put(statusCode, "404");
				body.put("data", null);
				return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			}
		} else {
			body.put(message, "Customer not found with id: " + cid);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	
//	public List<Customer> getbyop(String op) {
//		return customerRepo.findByOperator(op);
//	}
	
}
