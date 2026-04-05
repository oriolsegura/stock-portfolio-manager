package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

	@GetMapping
	public String getPortfolio(@AuthenticationPrincipal User user) {
		return String.format("This is %s's portfolio", user.getUsername());
	}

}
