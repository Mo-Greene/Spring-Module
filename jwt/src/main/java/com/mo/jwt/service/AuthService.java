package com.mo.jwt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.mo.jwt.dto.AuthResponse;
import com.mo.jwt.jwt.JwtService;
import com.mo.jwt.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;


	public AuthResponse authenticate(User user) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
		);
		return new AuthResponse(jwtService.generateToken(user));
	}
}
