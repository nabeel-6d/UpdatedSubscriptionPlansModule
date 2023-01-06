package com.example.subscriptions.authentication.models;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class JwtResponse {

    private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}
    
}
