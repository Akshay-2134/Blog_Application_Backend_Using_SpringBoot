package com.akshay.blog.payloads;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
//	 private UserDetails user;

}
