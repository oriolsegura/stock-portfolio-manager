package com.oriolsegura.opulentia.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oriolsegura.opulentia.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

	@Value("${spring.application.key}")
	private String secret;

	public String generateToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		return JWT.create()
				.withIssuer("auth-api")
				.withSubject(user.getUsername())
				.withExpiresAt(generateExpirationDate())
				.sign(algorithm);
	}

	public String validateToken(String token) throws JWTVerificationException {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		return JWT.require(algorithm)
				.withIssuer("auth-api")
				.build()
				.verify(token)
				.getSubject();
	}

	private Instant generateExpirationDate() {
		return Instant.now().plus(Duration.ofHours(2));
	}

}
