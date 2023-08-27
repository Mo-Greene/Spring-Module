package com.mo.jwt;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mo.jwt.model.User;
import com.mo.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		List<User> userList = List.of(
			User.builder()
				.email("13blueboy13@naver.com")
				.password(passwordEncoder.encode("1234"))
				.build()
		);
		userRepository.saveAll(userList);
	}
}
