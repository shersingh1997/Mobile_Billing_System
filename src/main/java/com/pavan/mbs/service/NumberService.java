package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.Numbers;
import com.pavan.mbs.repo.NumbersRepo;

@Service
public class NumberService {

	@Autowired
	private NumbersRepo numberRepo;

	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";

	Map<String, String> body = new HashMap<>();

	public ResponseEntity<Map<String, String>> addNumbers(List<Numbers> numbers) {
		List<Numbers> n = numberRepo.saveAll(numbers);
		body.put(message, "Mobile numbers added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", n.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Map<String, String>> addNumber(Numbers number) {
		Numbers n = numberRepo.save(number);
		body.put(message, "Mobile number addedd Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", n.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}

	public ResponseEntity<Map<String, String>> getNumbers() {
		List<Numbers> numbers = numberRepo.findAll();
		body.put(message, "List of mobile numbers");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", numbers.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}

	public ResponseEntity<Map<String, String>> updateNumber(Numbers number) {
		Optional<Numbers> existing = numberRepo.findById(number.getId());
		if (existing.isPresent()) {
			Numbers saved = numberRepo.save(number);
			body.put(message, "Mobile number updated");
			body.put(status, "true");
			body.put(statusCode, "200");
			body.put("data", saved.toString());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			body.put(message, "Number not found with id: " + number.getId());
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Map<String, String>> deleteNumber(int id) {
		Optional<Numbers> existing = numberRepo.findById(id);
		if (existing.isPresent()) {
			Numbers n = existing.get();
			numberRepo.delete(n);
			body.put(message, "Mobile number deleted");
			body.put(status, "true");
			body.put(statusCode, "200");
			body.put("data", n.toString());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			body.put(message, "Number not found with id: " + id);
			body.put(status, "false");
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
}
