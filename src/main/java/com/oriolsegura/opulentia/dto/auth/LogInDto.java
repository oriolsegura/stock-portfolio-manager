package com.oriolsegura.opulentia.dto.auth;

import jakarta.validation.constraints.NotNull;

public record LogInDto(
		@NotNull String username,
		@NotNull String password
) {

	//

}
