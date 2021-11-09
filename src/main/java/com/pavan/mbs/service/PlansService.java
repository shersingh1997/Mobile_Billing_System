package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.pavan.mbs.entity.Plans;
import com.pavan.mbs.repo.PlansRepo;

@Service
public class PlansService {

	@Autowired
	private PlansRepo plansRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	Map<String, String> body = new HashMap<>();
	
	public ResponseEntity<Map<String, String>> addPlan(Plans plan) {
		Plans planSaved = plansRepo.save(plan);
		body.put(message, "Plan Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", planSaved.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public ResponseEntity<Map<String, String>> addPlans(List<Plans> plans) {
		List<Plans> planSaved = plansRepo.saveAll(plans);
		body.put(message, "Plans Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", planSaved.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public ResponseEntity<Map<String, String>> getPlanById(int id) {
		Optional<Plans> plan = plansRepo.findById(id);
		if(plan.isPresent()) {
			body.put(message, "Plan details found");
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", plan.get().toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);			
		} else {
			body.put(message, "Plan details not found");
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	} 
	public ResponseEntity<Map<String, String>> getAllPlans() {
		List<Plans> plans = plansRepo.findAll();
		body.put(message, "All Mobile plans");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", plans.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
	public ResponseEntity<Map<String, String>> getPlanByOperator(String operator) {
		List<Plans> plans = plansRepo.findByOperator(operator);
		body.put(message, "All " + operator + " plans");
		body.put(status, "true");
		body.put(statusCode, "200");
		body.put("data", plans.toString());
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
