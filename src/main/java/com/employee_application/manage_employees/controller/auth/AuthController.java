package com.employee_application.manage_employees.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee_application.manage_employees.model.auth.AuthResponse;
import com.employee_application.manage_employees.model.register.RegisterRequest;
import com.employee_application.manage_employees.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private AuthService authService;
	
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }
    
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") RegisterRequest registerRequest,
            BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
        	model.addAttribute("errorMessage", "There are few errors in the information you've entered");
        	return "registerPage";
        }
        authService.register(registerRequest);
	    return "redirect:/login";
    }
}