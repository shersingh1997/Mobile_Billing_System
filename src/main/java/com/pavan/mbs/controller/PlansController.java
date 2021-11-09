package com.pavan.mbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Plans;
import com.pavan.mbs.service.PlansService;

@RestController
public class PlansController {
	
	@Autowired
	private PlansService plansService;
	
	@PostMapping("/plan")
	public ResponseEntity<Map<String, String>> addPlan(@RequestBody Plans plan) {
		return plansService.addPlan(plan);
	}
	@PostMapping("/plans")
	public ResponseEntity<Map<String, String>> addPlans(@RequestBody List<Plans> plans) {
		return plansService.addPlans(plans);
	}
	@GetMapping("/plans")
	public ResponseEntity<Map<String, String>> getAllPlans() {
		return plansService.getAllPlans();
	}
	@GetMapping("/plans/operator={operator}")
	public ResponseEntity<Map<String, String>> getPlansByOperator(@PathVariable String operator) {
		return plansService.getPlanByOperator(operator);
	}
	@GetMapping("/plan/{id}")
	public ResponseEntity<Map<String, String>> getPlansById(@PathVariable int id) {
		return plansService.getPlanById(id);
	}
}
