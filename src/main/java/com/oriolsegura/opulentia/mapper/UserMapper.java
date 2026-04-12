package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.request.auth.SignUpRequest;
import com.oriolsegura.opulentia.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User fromSignUpRequest(SignUpRequest request) {
		User user = new User();
		user.setUsername(request.username());
		user.setEmail(request.email());

		return user;
	}

}
