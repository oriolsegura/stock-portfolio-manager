package com.oriolsegura.opulentia.dto.auth;

import jakarta.validation.constraints.NotNull;

public record SignUpDto(
		@NotNull String username,
		@NotNull String email,
		@NotNull String password
) {

	//

}
