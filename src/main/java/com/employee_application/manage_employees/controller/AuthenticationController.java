package com.employee_application.manage_employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee_application.manage_employees.exception.AuthenticationException;
import com.employee_application.manage_employees.model.AuthenticationRequest;
import com.employee_application.manage_employees.model.AuthenticationResponse;
import com.employee_application.manage_employees.security.util.JwtUtil;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtUtil jwtTokenUtil;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			
		}catch(Exception ex) {
			throw new AuthenticationException(ex.getMessage());
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.ACCEPTED);
	}
}