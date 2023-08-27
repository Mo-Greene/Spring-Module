package com.mo.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mo.jwt.dto.AuthRequest;
import com.mo.jwt.dto.AuthResponse;
import com.mo.jwt.model.User;
import com.mo.jwt.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
		User user = User.builder()
			.email(login.email())
			.password(login.password())
			.build();

		return ResponseEntity.ok(authService.authenticate(user));
	}
}
