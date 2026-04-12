package com.oriolsegura.opulentia.filter;

import com.oriolsegura.opulentia.exception.auth.InvalidTokenException;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.repository.UserRepository;
import com.oriolsegura.opulentia.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
		try {
			String token = recoverToken(request);

			if (token != null) {
				authenticate(token);
			}

			filterChain.doFilter(request, response);
		} catch (AuthenticationException e) {
			SecurityContextHolder.clearContext();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}

	private void authenticate(String token) throws InvalidTokenException {
		String username = tokenService.validateToken(token);

		User user = userRepository.findByUsername(username)
				.orElseThrow(InvalidTokenException::new);

		var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
