package com.oriolsegura.opulentia.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record SignUpRequest(
		@NotBlank
		@Size(max = 31)
		String username,
		@Email
		@Size(max = 127)
		String email,
		@NotBlank
		@Size(min = 8, max = 255)
		String password
) {

	//

}
