package com.pavan.mbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Request;
import com.pavan.mbs.service.LoginService;


@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Request request) {		
		return loginService.login(request);
	}
	
}
