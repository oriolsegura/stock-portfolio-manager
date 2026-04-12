package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.request.auth.LogInRequest;
import com.oriolsegura.opulentia.request.auth.SignUpRequest;
import com.oriolsegura.opulentia.dto.auth.AuthenticationDto;
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
	public ResponseEntity<AuthenticationDto> signUp(@ModelAttribute @Valid SignUpRequest request) {
		String token = authService.signUp(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationDto(token));
	}

	@PostMapping(value = "/auth/log-in", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<AuthenticationDto> logIn(@ModelAttribute @Valid LogInRequest request) {
		String token = authService.logIn(request);

		return ResponseEntity.ok(new AuthenticationDto(token));
	}

}
