package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.dto.auth.SignUpDto;
import com.oriolsegura.opulentia.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User fromSignUpRequest(SignUpDto data) {
		User user = new User();
		user.setUsername(data.username());
		user.setEmail(data.email());

		return user;
	}

}
