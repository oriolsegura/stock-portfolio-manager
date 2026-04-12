package com.oriolsegura.opulentia.dto.auth;

import java.time.Instant;

public record TokenDto(String token, Instant expiresAt) {

	//

}
