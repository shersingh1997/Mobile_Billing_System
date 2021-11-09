package com.pavan.mbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Numbers;
import com.pavan.mbs.service.NumberService;

@RestController
public class NumbersController {

	@Autowired
	private NumberService numberService;
	
	@PostMapping("/number")
	public ResponseEntity<Map<String, String>> addNumber(@RequestBody Numbers number) {
		return numberService.addNumber(number);
	}

	@PostMapping("/numbers")
	public ResponseEntity<Map<String, String>> addNumbers(@RequestBody List<Numbers> numbers) {
		return numberService.addNumbers(numbers);
	}
	
	@GetMapping("/numbers")
	public ResponseEntity<Map<String, String>> getNumbers() {
		return numberService.getNumbers();
	}
	
	@PutMapping("/number")
	public ResponseEntity<Map<String, String>> updateNumber(@RequestBody Numbers number) {
		return numberService.updateNumber(number);
	}
	
	@DeleteMapping("/number/{id}")
	public ResponseEntity<Map<String, String>> deleteNumber(@PathVariable int id) {
		return numberService.deleteNumber(id);
	}
}
