package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.Request;
import com.pavan.mbs.repo.CustomerRepo;

@Service
public class LoginService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	public ResponseEntity<Map<String, String>> login(Request request) {
		String username = request.getEmail();
		String password = request.getPassword();
		String type = request.getType();
		Map<String, String> body = new HashMap<>();
		Customer customer = customerRepo.findByEmail(username);
		if(customer == null) {
			body.put(message, "User not found");
			body.put(status, "false");
			body.put(statusCode, "404");
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		} else {
			if(username.equals(customer.getEmail())) {
				if(password.equals(customer.getPassword())) {
					if(type.equals(customer.getType())) {
						body.put(message, "Login Successful!");
						body.put(status, "true");
						body.put(statusCode, "200");
						return new ResponseEntity<>(body, HttpStatus.FOUND);						
					} else {
						body.put(message, "Unauthorized Credentials");
						body.put(status, "false");
						body.put(statusCode, "403");
						return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
					}
				} else {
					body.put(message, "Password Incorrect!");
					body.put(status, "false");
					body.put(statusCode, "401");
					return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
				}
			} else {
				body.put(message, "Invalid Username");
				body.put(status, "false");
				body.put(statusCode, "401");
				return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
			}
		}
		
	}
}
