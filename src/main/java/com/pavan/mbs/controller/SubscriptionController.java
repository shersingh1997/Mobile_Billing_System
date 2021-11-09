package com.pavan.mbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.pavan.mbs.entity.Subscription;
import com.pavan.mbs.service.SubscriptionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

	
	@Autowired
	private SubscriptionService subsService;
	
	@PostMapping("/customer/{cid}/mobile/{mid}/subscribe")
	public ResponseEntity<Map<String, String>> subscribe(@RequestBody Subscription subs, @PathVariable int cid, @PathVariable int mid) {
		return subsService.subscribe(cid, mid, subs);
	}
	
	@GetMapping("/customer/{cid}/mobile/{mid}/subscriptions")
	public ResponseEntity<Map<String, String>> getSubscriptions(@PathVariable int cid, @PathVariable int mid) {
		return subsService.getSubscriptions(cid, mid);
	}
}