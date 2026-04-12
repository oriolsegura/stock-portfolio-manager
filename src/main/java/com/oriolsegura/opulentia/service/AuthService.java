package com.oriolsegura.opulentia.service;

import com.oriolsegura.opulentia.request.auth.LogInRequest;
import com.oriolsegura.opulentia.request.auth.SignUpRequest;
import com.oriolsegura.opulentia.exception.auth.UserAlreadyExistsException;
import com.oriolsegura.opulentia.exception.auth.WrongCredentialsException;
import com.oriolsegura.opulentia.mapper.UserMapper;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final UserRepository repository;

	private final PasswordEncoder encoder;

	private final TokenService tokenService;

	private final UserMapper mapper;

	public AuthService(UserRepository repository, PasswordEncoder encoder, TokenService tokenService, UserMapper mapper) {
		this.repository = repository;
		this.encoder = encoder;
		this.tokenService = tokenService;
		this.mapper = mapper;
	}

	public String signUp(SignUpRequest request) throws UserAlreadyExistsException {
		try {
			User user = mapper.fromSignUpRequest(request);
			user.setPassword(encoder.encode(request.password()));
			user = repository.save(user);

			return tokenService.generateToken(user);
		} catch (DataIntegrityViolationException e) {
			throw new UserAlreadyExistsException();
		}
	}

	public String logIn(LogInRequest request) throws WrongCredentialsException {
		User user = repository.findByUsername(request.username())
				.filter(u -> encoder.matches(request.password(), u.getPassword()))
				.orElseThrow(WrongCredentialsException::new);

		return tokenService.generateToken(user);
	}

}
