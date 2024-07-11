package com.employee_application.manage_employees.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.model.auth.AuthResponse;
import com.employee_application.manage_employees.model.register.RegisterRequest;
import com.employee_application.manage_employees.security.jwt.JwtUtil;
import com.employee_application.manage_employees.service.myuser.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Autowired
    private UserService userService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  JwtUtil jwtUtil;


    public AuthResponse login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public void register(RegisterRequest registerRequest) {
        userService.processRegisterRequest(registerRequest);
    }
}
