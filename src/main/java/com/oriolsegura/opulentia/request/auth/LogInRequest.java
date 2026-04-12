package com.oriolsegura.opulentia.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record LogInRequest(
		@NotBlank
		@Size(max = 31)
		String username,
		@NotBlank
		@Size(min = 8, max = 255)
		String password
) {

	//

}
