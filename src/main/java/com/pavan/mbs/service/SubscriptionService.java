package com.pavan.mbs.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.Mobile;
import com.pavan.mbs.entity.Plans;
import com.pavan.mbs.entity.Subscription;
import com.pavan.mbs.repo.CustomerRepo;
import com.pavan.mbs.repo.MobileRepo;
import com.pavan.mbs.repo.PlansRepo;

@Service
public class SubscriptionService {

	@Autowired
	private CustomerRepo custRepo;
	@Autowired
	private MobileRepo mobileRepo;
	@Autowired
	private PlansRepo planRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	Map<String, String> body = new HashMap<>();
	
	public ResponseEntity<Map<String, String>> subscribe(int cid, int mid, Subscription subscription) {			
		Optional<Customer> c = custRepo.findById(cid);
		if(c.isPresent()) {
			Customer cu = c.get();
			Optional<Mobile> m = mobileRepo.findById(mid);
			if(m.isPresent()) {
				
				Plans plan = planRepo.getById(subscription.getPlanId());
				List<Subscription> subs = m.get().getSubscriptions();
				Calendar now = Calendar.getInstance();
				String curr = (now.get(Calendar.MONTH) + 1) + "-"+ now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR);
				now.add(Calendar.DATE, plan.getDuration());
				String end = (now.get(Calendar.MONTH) + 1) + "-"+ now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR);
				subscription.setStartDate(curr);
				subscription.setEndDate(end);
				subs.add(subscription);
				cu = custRepo.save(cu);
				body.put(message, "Plan subscribed Successfully");
				body.put(status, "true");
				body.put(statusCode, "201");
				body.put("data", cu.toString());
				return new ResponseEntity<>(body, HttpStatus.CREATED);
			} else {
				body.put(message, "Mobile number not found with id: " + mid);
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
	public ResponseEntity<Map<String, String>> getSubscriptions(int cid, int mid) {
		Optional<Customer> c = custRepo.findById(cid);
		if(c.isPresent()) {
			Optional<Mobile> m = mobileRepo.findById(mid);
			if(m.isPresent()) {
				List<Subscription> subs = m.get().getSubscriptions();
				body.put(message, "Plans Subscribed on mobile: " + m.get().getNumber());
				body.put(status, "true");
				body.put(statusCode, "200");
				body.put("data", subs.toString());
				return new ResponseEntity<>(body, HttpStatus.OK);
			} else {
				body.put(message, "Mobile number not found with id: " + mid);
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
}
