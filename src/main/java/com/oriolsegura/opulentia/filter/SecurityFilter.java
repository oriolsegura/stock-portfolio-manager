package com.oriolsegura.opulentia.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.repository.UserRepository;
import com.oriolsegura.opulentia.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final TokenService tokenService;

	private final UserRepository userRepository;

	public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		String token = recoverToken(request);

		if (token != null) {
			authenticate(token);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}

	private void authenticate(String token) {
		try {
			String username = tokenService.validateToken(token);

			userRepository.findByUsername(username).ifPresent(user -> {
				var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			});
		} catch (JWTVerificationException ignored) {
			SecurityContextHolder.clearContext();
		}
	}

}
