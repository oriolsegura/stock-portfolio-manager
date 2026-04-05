package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.dto.auth.LogInDto;
import com.oriolsegura.opulentia.dto.auth.SignUpDto;
import com.oriolsegura.opulentia.dto.user.LoginResponseDto;
import com.oriolsegura.opulentia.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/auth/sign-up", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<LoginResponseDto> signUp(@ModelAttribute @Valid SignUpDto data) {
		String token = authService.signUp(data);

		return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponseDto(token));
	}

	@PostMapping(value = "/auth/log-in", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<LoginResponseDto> logIn(@ModelAttribute @Valid LogInDto data) {
		String token = authService.logIn(data);

		return ResponseEntity.ok(new LoginResponseDto(token));
	}

}
