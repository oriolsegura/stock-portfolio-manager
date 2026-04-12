package com.oriolsegura.opulentia.dto.auth;

public record AuthenticationDto(String token, Long expiresAt, String tokenType) {

	//

}
