package com.enfec.bookstatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enfec.bookstatus.model.User;

import com.enfec.bookstatus.model.JwtResponse;
import com.enfec.bookstatus.service.JwtProvider;

@RestController

@RequestMapping("/login")
public class JwtAuthenticationController {

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody User pk) {

		String token = jwtProvider.generateJwtToken(pk);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
