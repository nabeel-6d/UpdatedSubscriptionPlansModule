package com.example.subscriptions.authentication.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtRequest {

    private String username;
    private String password;
    
    
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
    
}
